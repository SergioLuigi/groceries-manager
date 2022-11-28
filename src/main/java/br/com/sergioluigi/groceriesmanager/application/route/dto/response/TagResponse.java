package br.com.sergioluigi.groceriesmanager.application.route.dto.response;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import lombok.Data;

@Data
public class TagResponse {

    private String id;

    private String description;

    public TagResponse(Tag tag) {
        this.id = tag.getId().toString();
        this.description = tag.getDescription();
    }
}
