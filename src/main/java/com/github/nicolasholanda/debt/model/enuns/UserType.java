package com.github.nicolasholanda.debt.model.enuns;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum UserType {

    SELLER(1, "Vendedor"),
    CUSTOMER(2, "Comprador");

    private Integer code;
    private String description;

    UserType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static UserType of(Integer code) {
        return stream(values()).filter(n -> n.getCode().equals(code)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException(format("Não existe tipo de usuário com código %s.", code));
        });
    }
}
