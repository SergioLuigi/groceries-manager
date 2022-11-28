package br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import org.bson.types.ObjectId;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductUpdateInport {

    Mono<Product> execute(@NotNull ObjectId id, @Valid Product updatedPooduct);
}
