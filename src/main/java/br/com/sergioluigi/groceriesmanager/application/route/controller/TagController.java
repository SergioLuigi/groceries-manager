package br.com.sergioluigi.groceriesmanager.application.route.controller;

import br.com.sergioluigi.groceriesmanager.application.route.dto.request.TagRegisterRequest;
import br.com.sergioluigi.groceriesmanager.application.route.dto.response.TagResponse;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.in.FindTagPageByFirstThreeLettersInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.in.TagRegisterInPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagRegisterInPort tagRegisterInPort;

    private final FindTagPageByFirstThreeLettersInPort findTagPageByFirstThreeLettersInPort;

    @PostMapping
    public Mono<TagResponse> register(@RequestBody @Valid TagRegisterRequest tagRegisterRequest){
            return tagRegisterInPort
                        .execute(tagRegisterRequest.getDescription())
                        .map(TagResponse::new);
    }

    @GetMapping
    public Mono<Page<TagResponse>> findTagPageByDescriptionStartingWithIgnoreCase(@RequestParam("letters") String letters,
                                                                                  @PageableDefault(sort = "description") Pageable pageable){
        return findTagPageByFirstThreeLettersInPort.execute(letters, pageable)
                .map(page -> page.map(TagResponse::new));
    }

}
