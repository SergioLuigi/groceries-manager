package br.com.sergioluigi.groceriesmanager.domain.exception;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Order(-2)
@Component
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalExceptionHandler(ErrorAttributes errorAttributes,
                                  WebProperties webProperties,
                                  ApplicationContext applicationContext,
                                  ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, webProperties.getResources(), applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions
                .route(RequestPredicates.all(), this::formatErrorResponse);
    }

    private Mono<ServerResponse> formatErrorResponse(ServerRequest request){

        var errorAttributesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        var statusCode = (int) Optional.ofNullable(errorAttributesMap.get("status")).orElse(500);

        var exception = getError(request);

        if (exception instanceof WebExchangeBindException) {
            var messages = extractErrorMessages((WebExchangeBindException) exception);
            errorAttributesMap.put("message", "property value error");
            errorAttributesMap.put("propertiesErrors", messages);
        }else if(exception instanceof ResponseStatusException){
            var reason = ((ResponseStatusException) exception).getReason();
            errorAttributesMap.put("message", reason);
        }else {
            errorAttributesMap.put("message", exception.getMessage());
        }

        return ServerResponse
                .status(statusCode)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAttributesMap));
    }

    private static Map<String, String> extractErrorMessages(WebExchangeBindException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> {
                    if(objectError instanceof FieldError){
                        return Map.of(((FieldError) objectError).getField(), Objects.requireNonNull(objectError.getDefaultMessage()));
                    }else{
                        return Map.of(objectError.getObjectName(), Objects.requireNonNull(objectError.getDefaultMessage()));
                    }
                })
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }


}
