package br.com.sergioluigi.groceriesmanager.infrastructure.database.document;

import br.com.sergioluigi.groceriesmanager.domain.model.MeasurementUnit;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Document("product")
@RequiredArgsConstructor
public class ProductDocument {

    @MongoId
    private String id;

    @Indexed(unique = true)
    private String name;

    private String brand;

    private MeasurementUnit measurementUnit;

    //private Suplier suplier;

    private BigDecimal value;

    @DocumentReference
    private Set<TagDocument> tags;

    public ProductDocument(Product product){
        this.name = product.getName();
        this.brand = product.getBrand();
        this.measurementUnit = product.getMeasurementUnit();
        this.value = product.getValue();
        this.tags = product.getTags()
                .stream()
                .map(tag -> new TagDocument(tag.getId(),tag.getDescription()))
                .collect(Collectors.toSet());
    }

    @Transient
    public Product toProduct(){
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .brand(this.brand)
                .measurementUnit(this.getMeasurementUnit())
                .value(this.getValue())
                .tags(this.getTags().stream().map(TagDocument::toTag)
                        .collect(Collectors.toSet()))
                .build();
    }
}
