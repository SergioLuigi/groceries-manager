package br.com.sergioluigi.groceriesmanager.application.route.dto.request;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TagRegisterRequest extends Tag {

    @JsonCreator
    public TagRegisterRequest(@NotBlank @Size(min = 3, max = 20) String description) {
        super(description);
    }
}
