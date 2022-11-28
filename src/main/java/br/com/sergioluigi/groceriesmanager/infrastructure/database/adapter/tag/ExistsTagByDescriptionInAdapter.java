package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter.tag;

import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.ExistsTagByDescriptionInOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ExistsTagByDescriptionInAdapter implements ExistsTagByDescriptionInOutPort {

    private final TagRepository tagRepository;

    @Override
    public Boolean existsTagByDescriptionIn(List<String> descriptions) {
        return tagRepository.existsByDescriptionIn(descriptions);
    }
}
