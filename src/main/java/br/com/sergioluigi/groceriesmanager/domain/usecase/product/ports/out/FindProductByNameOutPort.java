package br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;

import java.util.Optional;

public interface FindProductByNameOutPort {
    Optional<Product> findProductByName(String name);
}
