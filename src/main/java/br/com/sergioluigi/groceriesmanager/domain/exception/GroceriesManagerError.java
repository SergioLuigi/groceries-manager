package br.com.sergioluigi.groceriesmanager.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GroceriesManagerError {
    TAG_ALREADY_EXISTS(HttpStatus.CONFLICT, "tag.already.exists"),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "tag.not.found"),

    ONE_OR_MORE_TAGS_WERE_NOT_FOUND_DURING_PRODUCT_REGISTER(HttpStatus.NOT_FOUND, "one.or.more.tags.were.not.found.during.product.register"),

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "product.not.found"),
    PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT, "product.already.exists");

    private final HttpStatus httpStatus;

    private final String reason;
}
