package br.com.sergioluigi.groceriesmanager.core.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GroceriesManagerError {

    TAG_ALREADY_EXISTS_EXCEPTION(HttpStatus.NOT_FOUND,"tag.already.exists.exception");

    private final HttpStatus httpStatus;
    private final String reason;



}
