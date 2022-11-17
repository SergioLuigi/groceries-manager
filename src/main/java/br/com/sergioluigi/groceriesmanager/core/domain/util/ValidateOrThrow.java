package br.com.sergioluigi.groceriesmanager.core.domain.util;

import br.com.sergioluigi.groceriesmanager.core.domain.exception.GroceriesManagerError;
import br.com.sergioluigi.groceriesmanager.core.domain.exception.GroceriesManagerException;
import org.slf4j.Logger;
import reactor.core.publisher.Mono;

public class ValidateOrThrow {

    static void trueOrThrow(Boolean testResult, GroceriesManagerError error, Logger logger){
        if (!testResult) {
            logger.error(error.getReason());
            Mono.error(new GroceriesManagerException(error));
        }
    }
}
