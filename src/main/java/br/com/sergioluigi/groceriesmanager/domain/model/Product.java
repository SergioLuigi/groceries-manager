package br.com.sergioluigi.groceriesmanager.domain.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class Product {

    private ObjectId id;

    @NotBlank
    @Size(min = 3, max = 150)
    private String name;

    @NotBlank
    @Size(min = 3, max = 150)
    private String brand;

    @NotNull
    private MeasurementUnit measurementUnit;

    //private Suplier suplier;

    @NotNull
    private BigDecimal value;

    @NotEmpty
    private Set<@Valid Tag> tags;

    public Product(String name,
                   String brand,
                   MeasurementUnit measurementUnit,
                   BigDecimal value,
                   Set<Tag> tags) {
        this.name = name;
        this.brand = brand;
        this.measurementUnit = measurementUnit;
        this.value = value;
        this.tags = tags;
    }

    @Transient
    public List<String> getTagDescriptions(){
        return this.getTags().stream().map(Tag::getDescription).collect(Collectors.toList());
    }
}
