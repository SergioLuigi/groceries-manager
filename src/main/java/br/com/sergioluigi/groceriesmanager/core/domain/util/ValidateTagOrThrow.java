package br.com.sergioluigi.groceriesmanager.core.domain.util;

import org.slf4j.Logger;

import static br.com.sergioluigi.groceriesmanager.core.domain.exception.GroceriesManagerError.TAG_ALREADY_EXISTS_EXCEPTION;

public class ValidateTagOrThrow {
    public static void trueOrThrowTagAlreadyExistsException(Boolean testResult, Logger logger) {
        ValidateOrThrow.trueOrThrow(testResult,TAG_ALREADY_EXISTS_EXCEPTION, logger);
    }


}
