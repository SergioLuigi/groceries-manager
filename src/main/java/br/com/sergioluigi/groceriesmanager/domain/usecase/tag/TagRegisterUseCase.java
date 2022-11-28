package br.com.sergioluigi.groceriesmanager.domain.usecase.tag;

import br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerException;
import br.com.sergioluigi.groceriesmanager.domain.model.Tag;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.in.TagRegisterInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.ExistsTagByDescriptionOutPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.tag.ports.out.TagRegisterOutPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static br.com.sergioluigi.groceriesmanager.domain.exception.GroceriesManagerError.TAG_ALREADY_EXISTS;

@Slf4j
@Service
@AllArgsConstructor
public class TagRegisterUseCase implements TagRegisterInPort {

    private final ExistsTagByDescriptionOutPort existsTagByDescriptionOutPort;

    private final TagRegisterOutPort tagRegisterOutPort;

    @Override
    @Transactional
    public Mono<Tag> execute(String description) {
        return Mono.just(description)
                .map(mapUniqueTagDescriptionOrThrow())
                .map(Tag::new)
                .map(tagRegisterOutPort::tagRegister)
                .doOnSuccess(savedTag -> log.info("DATA CREATED {}", savedTag))
                .doOnError(throwable -> log.error("ERROR: {}", throwable.getMessage()));
    }

    private Function<String, String> mapUniqueTagDescriptionOrThrow(){
        return description ->{
            if (existsTagByDescriptionOutPort.existsTagByDescription(description))
                throw new GroceriesManagerException(TAG_ALREADY_EXISTS);
            return description;
        };
    }

}
