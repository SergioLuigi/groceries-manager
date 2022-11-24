package br.com.sergioluigi.groceriesmanager.application.route.dto.request;

import br.com.sergioluigi.groceriesmanager.domain.model.MeasurementUnit;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.model.Tag;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

public class ProductRegisterRequest extends Product {

    public ProductRegisterRequest(@NotBlank @Size(min = 3, max = 150) String name,
                                  @NotBlank @Size(min = 3, max = 150) String brand,
                                  @NotNull MeasurementUnit measurementUnit,
                                  @NotNull BigDecimal value,
                                  @NotEmpty Set<@Valid Tag> tags) {
        super(name, brand, measurementUnit, value, tags);
    }
}
