package com.github.nicolasholanda.debt.model.enuns;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum PaymentStatus {

    PAID(1, "Quitado"),
    PENDING(2, "Pendente"),
    CANCELED(3, "Cancelado"),
    IN_PROCESS(4, "Em processo");

    private Integer code;
    private String description;

    PaymentStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static PaymentStatus of(Integer code) {
        return stream(values()).filter(n -> n.getCode().equals(code)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException(format("Não existe status de pagamento com código %s.", code));
        });
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
