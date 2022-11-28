package br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.in;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Validated
public interface FindTagPageByFirstThreeLettersInPort {
    Mono<Page<Tag>> execute(@Valid @Size(min = 3) String firstThreeLettersOrMore, Pageable pageable);
}
