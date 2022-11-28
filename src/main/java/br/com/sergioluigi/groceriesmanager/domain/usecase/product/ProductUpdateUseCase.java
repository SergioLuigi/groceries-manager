package br.com.sergioluigi.groceriesmanager.domain.usecase.product;

import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerException;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in.ProductUpdateInport;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.FindProductByIdOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.FindProductByNameOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.ProductRegisterOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.FindAllTagsByDescriptionsOutPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;

import static br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError.*;

@Slf4j
@Service
@AllArgsConstructor
public class ProductUpdateUseCase implements ProductUpdateInport {

    private final FindProductByIdOutPort findProductByIdOutPort;

    private final FindProductByNameOutPort findProductByNameOutPort;

    private final ProductRegisterOutPort productRegisterOutPort;

    private final FindAllTagsByDescriptionsOutPort findAllTagsByDescriptionsOutPort;

    @Override
    public Mono<Product> execute(ObjectId id, Product updatedPooduct) {
        return Mono.justOrEmpty(findProductByIdOutPort.findProductById(id))
                .switchIfEmpty(Mono.error(new GroceriesManagerException(PRODUCT_NOT_FOUND)))
                .doOnNext(uniqueProductNameOrThrow(updatedPooduct.getName()))
                .doOnNext(validateTagsOrThrow(updatedPooduct))
                .map(prepareProductToUpdate(updatedPooduct))
                .map(productRegisterOutPort::register);
    }

    private Function<Product, Product> prepareProductToUpdate(Product updatedPooduct) {
        return p -> {
            updatedPooduct.setId(p.getId());
            return updatedPooduct;
        };
    }

    private Consumer<Product> uniqueProductNameOrThrow(String newProductName) {
        return product1 -> {
            findProductByNameOutPort.findProductByName(newProductName)
                    .ifPresent(product2 -> {
                        if (!product1.getId().equals(product2.getId()) && product1.getName().equals(product2.getName())) {
                            throw new GroceriesManagerException(PRODUCT_ALREADY_EXISTS);
                        }
                    });
                };
    }

    private Consumer<Product> validateTagsOrThrow(Product updatedPooduct){
        return product -> {
            var descriptions = updatedPooduct.getTagDescriptions();
            var tags = findAllTagsByDescriptionsOutPort.findAllTagsByDescriptionIn(descriptions);
            if (tags.isEmpty() || tags.size() != descriptions.size())
                throw new GroceriesManagerException(ONE_OR_MORE_TAGS_WERE_NOT_FOUND_DURING_PRODUCT_REGISTER);
            updatedPooduct.setTags(tags);
        };
    }
}
