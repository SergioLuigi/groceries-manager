package br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out;

import java.util.List;

public interface ExistsTagByDescriptionInOutPort {
    Boolean existsTagByDescriptionIn(List<String> descriptions);
}
