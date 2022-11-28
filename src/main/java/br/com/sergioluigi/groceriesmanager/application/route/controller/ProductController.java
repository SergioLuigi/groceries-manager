package br.com.sergioluigi.groceriesmanager.application.route.controller;

import br.com.sergioluigi.groceriesmanager.application.route.dto.request.ProductRegisterRequest;
import br.com.sergioluigi.groceriesmanager.application.route.dto.response.ProductResponse;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in.FindProductPageByFirstThreeLettersInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in.ProductRegisterInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in.ProductUpdateInport;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRegisterInPort productRegisterInPort;

    private final FindProductPageByFirstThreeLettersInPort findProductPageByFirstThreeLettersInPort;

    private final ProductUpdateInport productUpdateInport;

    @PostMapping
    public Mono<ProductResponse> register(@RequestBody @Valid ProductRegisterRequest productRegisterRequest){
        return productRegisterInPort.execute(productRegisterRequest)
                .map(ProductResponse::new);
    }

    @PutMapping("/{id}")
    public Mono<ProductResponse> update(@PathVariable("id") ObjectId id, @RequestBody @Valid ProductRegisterRequest productRegisterRequest){
        return productUpdateInport.execute(id, productRegisterRequest)
                .map(ProductResponse::new);
    }

    @GetMapping
    public Mono<Page<ProductResponse>> findProductPageByNameStartingWithIgnoreCase(@RequestParam("letters") @Valid @Size(min = 3) String letters,
                                                                                    @PageableDefault(sort = "description") Pageable pageable){
        return findProductPageByFirstThreeLettersInPort.execute(letters, pageable)
                .map(page -> page.map(ProductResponse::new));
    }
}
