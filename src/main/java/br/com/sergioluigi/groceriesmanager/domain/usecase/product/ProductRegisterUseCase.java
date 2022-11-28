package br.com.sergioluigi.groceriesmanager.domain.usecase.product;

import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerException;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in.ProductRegisterInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.ExistsProductByNameOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.ProductRegisterOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.FindAllTagsByDescriptionsOutPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;

import static br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError.ONE_OR_MORE_TAGS_WERE_NOT_FOUND_DURING_PRODUCT_REGISTER;
import static br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError.PRODUCT_ALREADY_EXISTS;

@Slf4j
@Service
@AllArgsConstructor
public class ProductRegisterUseCase implements ProductRegisterInPort {
    private final ExistsProductByNameOutPort existsProductByNameOutPort;
    private final ProductRegisterOutPort productRegisterOutPort;
    private final FindAllTagsByDescriptionsOutPort findAllTagsByDescriptionsOutPort;

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
                product.setTags(tags);
            };
    }
}
