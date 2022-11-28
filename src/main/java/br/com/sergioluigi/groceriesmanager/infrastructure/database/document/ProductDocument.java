package br.com.sergioluigi.groceriesmanager.infrastructure.database.document;

import br.com.sergioluigi.groceriesmanager.domain.model.MeasurementUnit;
import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Document("product")
@RequiredArgsConstructor
public class ProductDocument {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String name;

    private String brand;

    private MeasurementUnit measurementUnit;

    private BigDecimal value;

    @DocumentReference
    private Set<TagDocument> tags;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public ProductDocument(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.measurementUnit = product.getMeasurementUnit();
        this.value = product.getValue();
        this.tags = product.getTags()
                .stream()
                .map(tag -> new TagDocument(tag.getId(),tag.getDescription()))
                .collect(Collectors.toSet());
    }

    public ProductDocument(ObjectId id, Product product){
        this.id = id;
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
                .value(this.value)
                .tags(this.tags
                        .stream()
                        .map(TagDocument::toTag)
                        .collect(Collectors.toSet())).build();
    }
}
