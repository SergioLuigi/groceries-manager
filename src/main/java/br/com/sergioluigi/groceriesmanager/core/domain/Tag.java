package br.com.sergioluigi.groceriesmanager.core.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface Tag {

    @NotBlank
    @Size(min = 3, max = 120)
    String getDescription();

}
