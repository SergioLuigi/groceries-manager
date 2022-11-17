package br.com.sergioluigi.groceriesmanager.core.domain.exception;

import org.springframework.web.server.ResponseStatusException;

public class GroceriesManagerException extends ResponseStatusException {

    public GroceriesManagerException(GroceriesManagerError error){
        super(error.getHttpStatus(), error.getReason());
    }
}
