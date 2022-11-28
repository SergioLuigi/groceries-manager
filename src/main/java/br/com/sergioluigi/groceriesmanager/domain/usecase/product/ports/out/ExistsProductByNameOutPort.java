package br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Validated
public interface ExistsProductByNameOutPort {
    Boolean existsProductByName(@Valid @NotBlank @Size(min = 3, max = 150) String name);
}
