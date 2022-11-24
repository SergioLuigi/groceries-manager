package br.com.sergioluigi.groceriesmanager.domain.usecase;

import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.in.FindTagPageByFirstThreeLettersInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.out.FindTagPageByDescriptionOutPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class FindTagPageByFirstThreeLettersUseCase implements FindTagPageByFirstThreeLettersInPort {

    private final FindTagPageByDescriptionOutPort findTagPageByDescriptionOutPort;

    public FindTagPageByFirstThreeLettersUseCase(
            @Qualifier("FindTagPageByDescriptionAdapter") FindTagPageByDescriptionOutPort findTagPageByDescriptionOutPort){
        this.findTagPageByDescriptionOutPort = findTagPageByDescriptionOutPort;
    }

    @Override
    public Mono<Page<Tag>> execute(String firstThreeLettersOrMore, Pageable pageable) {
        return Mono.just(firstThreeLettersOrMore)
                .map(letters -> findTagPageByDescriptionOutPort.findTagPageByDescriptionStartingWithIgnoreCase(letters, pageable));
    }
}
