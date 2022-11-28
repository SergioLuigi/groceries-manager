package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter.product;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.FindProductPageByNameOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.ProductDocument;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class FindProductPageByNameAdapter implements FindProductPageByNameOutPort {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findProductPageByNameStartingWithIgnoreCase(String description, Pageable pageable) {
        return productRepository.findByNameStartingWithIgnoreCase(description, pageable)
                .map(ProductDocument::toProduct);
    }
}
