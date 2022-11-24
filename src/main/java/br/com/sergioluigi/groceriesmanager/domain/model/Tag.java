package br.com.sergioluigi.groceriesmanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
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

