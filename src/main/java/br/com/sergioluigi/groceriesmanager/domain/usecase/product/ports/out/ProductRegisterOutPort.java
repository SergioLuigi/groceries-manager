package br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;

public interface ProductRegisterOutPort {
    public Product register(Product product);
}
