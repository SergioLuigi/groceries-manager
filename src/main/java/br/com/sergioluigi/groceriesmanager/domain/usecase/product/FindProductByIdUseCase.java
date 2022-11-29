package br.com.sergioluigi.groceriesmanager.domain.usecase.product;

import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError;
import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerException;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in.FindProductByIdInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.FindProductByIdOutPort;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FindProductByIdUseCase implements FindProductByIdInPort {

    private FindProductByIdOutPort findProductByIdOutPort;

    @Override
    public Mono<Product> execute(ObjectId id) {
        return Mono.justOrEmpty(findProductByIdOutPort.findProductById(id))
                .switchIfEmpty(Mono.error(new GroceriesManagerException(GroceriesManagerError.PRODUCT_NOT_FOUND)));
    }
}
