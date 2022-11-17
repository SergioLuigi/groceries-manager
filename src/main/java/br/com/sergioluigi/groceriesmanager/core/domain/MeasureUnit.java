package br.com.sergioluigi.groceriesmanager.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeasureUnit {
    LITER("L"),
    MILLILITER("ml"),
    KILOGRAM("kg"),
    GRAMS("g"),
    MILLIGRAMS("mg");

    private final String initials;
}