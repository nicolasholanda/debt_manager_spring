package com.github.nicolasholanda.debt.model.enuns;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum Gender {

    MALE(1, "Masculino"),
    FEMALE(2, "Feminino"),
    UNISEX(3, "Unissex");

    private Integer code;
    private String description;

    Gender(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Gender of(Integer code) {
        return stream(values()).filter(n -> n.getCode().equals(code)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException(format("Não existe o gênero com código %s.", code));
        });
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
