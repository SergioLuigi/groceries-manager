package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter.product;

import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.ExistsProductByNameOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExistsProductByNameAdapter implements ExistsProductByNameOutPort {

    private final ProductRepository productRepository;

    @Override
    public Boolean existsProductByName(String name) {
        return productRepository.existsByName(name);
    }
}
