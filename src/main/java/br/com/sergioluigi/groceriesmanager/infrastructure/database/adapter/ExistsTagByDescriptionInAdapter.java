package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter;

import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ExistsTagByDescriptionInOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ExistsTagByDescriptionOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository("ExistsTagByDescriptionInAdapter")
public class ExistsTagByDescriptionInAdapter implements ExistsTagByDescriptionInOutPort {

    private final TagRepository tagRepository;

    @Override
    public Boolean existsTagByDescriptionIn(List<String> descriptions) {
        return tagRepository.existsByDescriptionIn(descriptions);
    }
}
