package br.com.sergioluigi.groceriesmanager.domain.usecase;

import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerException;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.in.ProductRegisterInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ExistsProductByNameOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ExistsTagByDescriptionInOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ProductRegisterOutPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;

import static br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError.PRODUCT_ALREADY_EXISTS;
import static br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError.TAG_ALREADY_EXISTS;

@Slf4j
@Component
public class ProductRegisterUseCase implements ProductRegisterInPort {
    private final ExistsProductByNameOutPort existsProductByNameOutPort;
    private final ExistsTagByDescriptionInOutPort existsTagByDescriptionInOutPort;
    private final ProductRegisterOutPort productRegisterOutPort;

    public ProductRegisterUseCase(@Qualifier("ExistsProductByNameAdapter") final ExistsProductByNameOutPort existsProductByNameOutPort,
                                  @Qualifier("ExistsTagByDescriptionInAdapter") final ExistsTagByDescriptionInOutPort existsTagByDescriptionInOutPort,
                                  @Qualifier("ProductRegisterAdapter") final ProductRegisterOutPort productRegisterOutPort){
        this.existsProductByNameOutPort = existsProductByNameOutPort;
        this.existsTagByDescriptionInOutPort = existsTagByDescriptionInOutPort;
        this.productRegisterOutPort = productRegisterOutPort;
    }

    @Override
    public Mono<Product> execute(Product product) {
        return Mono.just(product)
                .map(mapUniqueProductNameOrThrow())
                .doOnNext(validateTagsExistence())
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

    private Consumer<Product> validateTagsExistence(){
        return product ->{
                var descriptions = product.getTagDescriptions();
                if (!existsTagByDescriptionInOutPort.existsTagByDescriptionIn(descriptions))
                    throw new GroceriesManagerException(TAG_ALREADY_EXISTS);
            };
    }
}
