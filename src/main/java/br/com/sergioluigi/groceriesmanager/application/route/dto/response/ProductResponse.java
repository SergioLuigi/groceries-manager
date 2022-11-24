package br.com.sergioluigi.groceriesmanager.application.route.dto.response;

import br.com.sergioluigi.groceriesmanager.domain.model.MeasurementUnit;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import lombok.Data;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProductResponse {

    private ObjectId id;

    private String name;

    private String brand;

    private MeasurementUnit measurementUnit;

    //private Suplier suplier;

    private BigDecimal value;

    private Set<TagResponse> tags;

    public ProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.measurementUnit = product.getMeasurementUnit();
        this.tags = product.getTags()
                .stream()
                .map(TagResponse::new)
                .collect(Collectors.toSet());
    }
}
