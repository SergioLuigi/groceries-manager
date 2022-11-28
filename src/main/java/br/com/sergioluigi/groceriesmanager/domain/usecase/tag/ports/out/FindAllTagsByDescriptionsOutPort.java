package br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;

import java.util.List;
import java.util.Set;

public interface FindAllTagsByDescriptionsOutPort {
    Set<Tag> findAllTagsByDescriptionIn(List<String> description);
}
