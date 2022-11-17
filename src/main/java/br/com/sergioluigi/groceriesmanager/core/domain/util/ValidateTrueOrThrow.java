package br.com.sergioluigi.groceriesmanager.core.domain.util;

import br.com.sergioluigi.groceriesmanager.core.domain.exception.GroceriesManagerError;
import org.slf4j.Logger;

@FunctionalInterface
public interface ValidateTrueOrThrow<LOG extends Logger> {

    void execute(Boolean func, GroceriesManagerError error, LOG logger);
}




