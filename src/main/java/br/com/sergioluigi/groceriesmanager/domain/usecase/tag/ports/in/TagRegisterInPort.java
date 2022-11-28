package br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.in;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Validated
public interface TagRegisterInPort {
    Mono<Tag> execute(@Valid @Size(min = 3, max = 20) String description);
}
