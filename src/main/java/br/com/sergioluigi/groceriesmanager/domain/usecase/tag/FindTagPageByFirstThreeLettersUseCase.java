package br.com.sergioluigi.groceriesmanager.domain.usecase.tag;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.in.FindTagPageByFirstThreeLettersInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.FindTagPageByDescriptionOutPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class FindTagPageByFirstThreeLettersUseCase implements FindTagPageByFirstThreeLettersInPort {

    private final FindTagPageByDescriptionOutPort findTagPageByDescriptionOutPort;

    @Override
    public Mono<Page<Tag>> execute(String firstThreeLettersOrMore, Pageable pageable) {
        return Mono.just(firstThreeLettersOrMore)
                .map(letters -> findTagPageByDescriptionOutPort.findTagPageByDescriptionStartingWithIgnoreCase(letters, pageable));
    }
}
