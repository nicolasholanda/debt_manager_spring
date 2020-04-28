package com.github.nicolasholanda.debt.model.enuns;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum PaymentType {

    CASH(1, "Dinheiro"),
    CREDIT(2, "Crédito");

    private Integer code;
    private String description;

    PaymentType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentType of(Integer code) {
        return stream(values()).filter(n -> n.getCode().equals(code)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException(format("Não existe tipo de pagamento com código %s.", code));
        });
    }
}
