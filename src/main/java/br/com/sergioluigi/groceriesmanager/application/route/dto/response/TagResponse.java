package br.com.sergioluigi.groceriesmanager.application.route.dto.response;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class TagResponse {

    private ObjectId id;

    private String description;

    public TagResponse(Tag tag) {
        this.id = tag.getId();
        this.description = tag.getDescription();
    }
}
