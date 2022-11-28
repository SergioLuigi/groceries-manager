package br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface FindProductByIdOutPort {
    Optional<Product> findProductById(ObjectId id);
}
