package br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface FindProductByIdInPort {
    Mono<Product> execute(ObjectId id);
}
