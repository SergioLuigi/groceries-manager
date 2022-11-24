package br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindTagPageByDescriptionOutPort {
    Page<Tag> findTagPageByDescriptionStartingWithIgnoreCase(String description, Pageable pageable);
}
