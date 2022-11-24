package br.com.sergioluigi.groceriesmanager.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GroceriesManagerError {
    TAG_ALREADY_EXISTS(HttpStatus.CONFLICT, "tag.already.exists"),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "tag.not.found"),
    PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT, "product.already.exists");

    private final HttpStatus httpStatus;

    private final String reason;
}
