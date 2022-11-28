package br.com.sergioluigi.groceriesmanager.domain.model;

import lombok.*;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Tag {

    private ObjectId id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String description;

    public Tag(String description){
        this.description = description;
    }

}

