package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter.tag;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.TagRegisterOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.TagDocument;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class TagRegisterAdapter implements TagRegisterOutPort {

    private final TagRepository tagRepository;

    @Override
    public Tag tagRegister(Tag tag) {
        return Optional.of(tag)
                .map(it -> new TagDocument(it.getDescription()))
                .map(tagRepository::save)
                .map(TagDocument::toTag)
                .orElseThrow();
    }
}
