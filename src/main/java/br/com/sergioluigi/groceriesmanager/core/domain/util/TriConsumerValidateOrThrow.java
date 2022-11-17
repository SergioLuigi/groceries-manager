package br.com.sergioluigi.groceriesmanager.core.domain.util;

@FunctionalInterface
public interface TriConsumerValidateOrThrow<R, V, S> {

    void accept(R r, V v, S s);
    
}