package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ProductRegisterOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.ProductDocument;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository("ProductRegisterAdapter")
public class ProductRegisterAdapter implements ProductRegisterOutPort {

    private final ProductRepository productRepository;

    @Override
    public Product register(Product product) {
        return Optional.ofNullable(product)
                .map(ProductDocument::new)
                .map(productRepository::save)
                .map(ProductDocument::toProduct)
                .orElseThrow();
    }
}
