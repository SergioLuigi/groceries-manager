package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter.product;

import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError;
import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerException;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.FindProductByIdOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.ProductDocument;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError.PRODUCT_NOT_FOUND;

@Repository
@AllArgsConstructor
public class FindProductByIdAdapter implements FindProductByIdOutPort {

    //private static final GroceriesManagerError PRODUCT_NOT_FOUND = ;
    private ProductRepository tagRepository;

    @Override
    public Optional<Product> findProductById(ObjectId id) {
        return tagRepository.findById(id)
                .map(ProductDocument::toProduct);

    }
}
