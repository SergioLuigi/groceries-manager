package br.com.sergioluigi.groceriesmanager.infrastructure.database.adapter.tag;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.FindAllTagsByDescriptionsOutPort;
import br.com.sergioluigi.groceriesmanager.infrastructure.database.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class FindAllTagsByDescriptionsAdapter implements FindAllTagsByDescriptionsOutPort {

    private final TagRepository tagRepository;

    @Override
    public Set<Tag> findAllTagsByDescriptionIn(List<String> description) {
        return tagRepository.findAllByDescriptionIn(description)
                .stream()
                .map(tagDocument -> new Tag(tagDocument.getId(), tagDocument.getDescription()))
                .collect(Collectors.toSet());
    }
}
