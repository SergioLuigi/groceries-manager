package br.com.sergioluigi.groceriesmanager.application.route.controller;

import br.com.sergioluigi.groceriesmanager.application.route.dto.request.ProductRegisterRequest;
import br.com.sergioluigi.groceriesmanager.application.route.dto.response.ProductResponse;
import br.com.sergioluigi.groceriesmanager.domain.usecase.ports.in.ProductRegisterInPort;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRegisterInPort productRegisterInPort;

    @PostMapping
    public Mono<ProductResponse> register(@RequestBody @Valid ProductRegisterRequest productRegisterRequest){
        return productRegisterInPort.execute(productRegisterRequest)
                .map(ProductResponse::new);
    }
}
