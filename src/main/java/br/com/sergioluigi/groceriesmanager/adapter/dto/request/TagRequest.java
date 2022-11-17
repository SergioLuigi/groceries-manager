package br.com.sergioluigi.groceriesmanager.adapter.dto.request;

import br.com.sergioluigi.groceriesmanager.core.domain.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TagRequest implements Tag {

    private String description;

    @Override
    public String getDescription() {
        return description;
    }
}
