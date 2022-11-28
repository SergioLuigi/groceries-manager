package br.com.sergioluigi.groceriesmanager.domain.usecase.product;

import br.com.sergioluigi.groceriesmanager.domain.model.Product;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.in.FindProductPageByFirstThreeLettersInPort;
import br.com.sergioluigi.groceriesmanager.domain.usecase.product.ports.out.FindProductPageByNameOutPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class FindProductPageByFirstThreeLettersUseCase implements FindProductPageByFirstThreeLettersInPort {

    private final FindProductPageByNameOutPort findProductPageByNameOutPort;

    @Override
    public Mono<Page<Product>> execute(String firstThreeLettersOrMore, Pageable pageable) {
        return Mono.just(firstThreeLettersOrMore)
                .map(letters -> findProductPageByNameOutPort
                        .findProductPageByNameStartingWithIgnoreCase(letters, pageable));
    }
}
