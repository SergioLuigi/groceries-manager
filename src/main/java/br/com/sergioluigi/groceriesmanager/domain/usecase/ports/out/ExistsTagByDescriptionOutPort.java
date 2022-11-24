package br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out;

public interface ExistsTagByDescriptionOutPort {
    Boolean existsTagByDescription(String description);
}
