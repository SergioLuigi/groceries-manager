package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter.product;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.FindProductByNameOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.ProductDocument;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class FindProductByNameAdapter implements FindProductByNameOutPort {

    private ProductRepository productRepository;

    @Override
    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name)
                .map(ProductDocument::toProduct);
    }
}
