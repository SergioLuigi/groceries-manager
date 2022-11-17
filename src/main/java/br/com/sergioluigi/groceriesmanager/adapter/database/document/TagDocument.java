package br.com.sergioluigi.groceriesmanager.adapter.database.document;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Document("tag")
public class TagDocument {

    @Id
    private ObjectId id;

    @NotBlank
    @Size(min = 3, max = 120)
    private final String description;

    public TagDocument(final String description){
        this.description = description;
    }
}
