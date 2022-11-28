package br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindProductPageByNameOutPort {
    Page<Product> findProductPageByNameStartingWithIgnoreCase(String name, Pageable pageable);
}
