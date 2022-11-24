package br.com.sergioluigi.groceriesmanager.domain.usecase;

import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerException;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.in.ProductRegisterInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ExistsProductByNameOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.FindAllTagsByDescriptionsOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ProductRegisterOutPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;

import static br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError.*;

@Slf4j
@Component
public class ProductRegisterUseCase implements ProductRegisterInPort {
    private final ExistsProductByNameOutPort existsProductByNameOutPort;
    private final ProductRegisterOutPort productRegisterOutPort;

    private final FindAllTagsByDescriptionsOutPort findAllTagsByDescriptionsOutPort;

    public ProductRegisterUseCase(@Qualifier("ExistsProductByNameAdapter") final ExistsProductByNameOutPort existsProductByNameOutPort,
                                  @Qualifier("FindAllTagsByDescriptionsAdapter") final FindAllTagsByDescriptionsOutPort findAllTagsByDescriptionsOutPort,
                                  @Qualifier("ProductRegisterAdapter") final ProductRegisterOutPort productRegisterOutPort){
        this.existsProductByNameOutPort = existsProductByNameOutPort;
        this.findAllTagsByDescriptionsOutPort = findAllTagsByDescriptionsOutPort;
        this.productRegisterOutPort = productRegisterOutPort;
    }

    @Override
    public Mono<Product> execute(Product product) {
        return Mono.just(product)
                .map(mapUniqueProductNameOrThrow())
                .doOnNext(prepareTags())
                .map(productRegisterOutPort::register)
                .doOnSuccess(savedProduct -> log.info("DATA CREATED {}", savedProduct))
                .doOnError(throwable -> log.error("ERROR: {}", throwable.getMessage()));
    }

    private Function<Product, Product> mapUniqueProductNameOrThrow() {
        return product -> {
            if (existsProductByNameOutPort.existsProductByName(product.getName()))
                throw new GroceriesManagerException(PRODUCT_ALREADY_EXISTS);
            return product;
        };
    }

    private Consumer<Product> prepareTags(){
        return product -> {
                var descriptions = product.getTagDescriptions();
                var tags = findAllTagsByDescriptionsOutPort.findAllTagsByDescriptionIn(descriptions);
                if (tags.isEmpty() || tags.size() != descriptions.size())
                    throw new GroceriesManagerException(ONE_OR_MORE_TAGS_WERE_NOT_FOUND_DURING_PRODUCT_REGISTER);
                product.setTags(new HashSet<>(tags));
            };
    }
}
