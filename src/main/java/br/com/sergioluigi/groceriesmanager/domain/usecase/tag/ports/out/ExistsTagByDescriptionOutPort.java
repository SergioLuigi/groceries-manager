package br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out;

public interface ExistsTagByDescriptionOutPort {
    Boolean existsTagByDescription(String description);
}
