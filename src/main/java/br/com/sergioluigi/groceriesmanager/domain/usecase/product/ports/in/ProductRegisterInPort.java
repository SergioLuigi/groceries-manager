package br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
public interface ProductRegisterInPort {

    Mono<Product> execute(@Valid Product product);

}
