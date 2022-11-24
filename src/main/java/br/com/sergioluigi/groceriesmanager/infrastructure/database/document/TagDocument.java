package br.com.sergioluigi.groceriesmanager.infrastructure.database.document;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("tag")
@RequiredArgsConstructor
public class TagDocument {

    @Id
    private ObjectId id;

    @Field
    @Indexed(unique = true)
    private String description;

    public TagDocument(String description){
        this.description = description;
    }

    public TagDocument(ObjectId id, String description){
        this.id = id;
        this.description = description;
    }

    @Transient
    public Tag toTag(){
        return new Tag(this.id, this.description);
    }
}
