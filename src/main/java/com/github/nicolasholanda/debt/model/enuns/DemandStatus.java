package com.github.nicolasholanda.debt.model.enuns;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum DemandStatus {
    NEW(1, "Novo"),
    ACCEPTED(2, "Aceito"),
    CANCELLED(3, "Cancelado"),
    DELIVERY(4, "Entrega"),
    DELIVERED(5, "Entregue");

    private Integer code;
    private String description;

    DemandStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static DemandStatus of(Integer code) {
        return stream(values()).filter(n -> n.getCode().equals(code)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException(format("Não existe o status do pedido com código %s.", code));
        });
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
