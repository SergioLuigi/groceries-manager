package br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port;


import br.com.sergioluigi.groceriesmanager.core.domain.Tag;
import br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port.in.TagRegisterInPort;
import br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port.out.TagExistsOutPort;
import br.com.sergioluigi.groceriesmanager.core.usecase.tag.tagregister.port.out.TagRegisterOutPort;
import static br.com.sergioluigi.groceriesmanager.core.domain.util.ValidateTagOrThrow.trueOrThrowTagAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static br.com.sergioluigi.groceriesmanager.core.domain.util.LogTemplate.DATA_CREATED;

@Slf4j
@Component
@RequiredArgsConstructor
public class TagRegister implements TagRegisterInPort {
54
    private final TagRegisterOutPort tagRegisterOutPort;

    private final TagExistsOutPort tagExistsOutPort;

    @Override
    @Transactional
    public void execute(@Valid Tag tag) {
        Mono.justOrEmpty(tag)
            .doOnNext(it -> trueOrThrowTagAlreadyExistsException(tagExistsOutPort.existsByDescription(it.getDescription()), log))
            .map(tagRegisterOutPort::registerTag)
            .doOnNext(it -> log.info(DATA_CREATED,it))
            .block();
    }
}


