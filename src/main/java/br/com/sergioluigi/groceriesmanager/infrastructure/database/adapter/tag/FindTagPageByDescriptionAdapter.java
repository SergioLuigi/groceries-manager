package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter.tag;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.FindTagPageByDescriptionOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.TagDocument;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class FindTagPageByDescriptionAdapter implements FindTagPageByDescriptionOutPort {

    private final TagRepository tagRepository;

    @Override
    public Page<Tag> findTagPageByDescriptionStartingWithIgnoreCase(String description, Pageable pageable) {
        return tagRepository.findByDescriptionStartingWithIgnoreCase(description, pageable)
                .map(TagDocument::toTag);
    }
}
