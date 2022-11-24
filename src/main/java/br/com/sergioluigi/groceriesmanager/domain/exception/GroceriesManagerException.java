package br.com.sergioluigi.groceriesmanager.domain.exception;

import org.springframework.web.server.ResponseStatusException;

public class GroceriesManagerException extends ResponseStatusException {

    public GroceriesManagerException(GroceriesManagerError groceriesManagerError){
        super(groceriesManagerError.getHttpStatus(), groceriesManagerError.getReason());
    }

}
