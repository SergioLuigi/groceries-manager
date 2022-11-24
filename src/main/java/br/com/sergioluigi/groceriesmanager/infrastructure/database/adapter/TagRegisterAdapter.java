package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.TagRegisterOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.document.TagDocument;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository("TagRegisterAdapter")
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
