package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter;

import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.ExistsTagByDescriptionOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository("ExistsTagByDescriptionAdapter")
public class ExistsTagByDescriptionAdapter implements ExistsTagByDescriptionOutPort {

    private final TagRepository tagRepository;

    @Override
    public Boolean existsTagByDescription(String description) {
        return tagRepository.existsByDescription(description);
    }
}
