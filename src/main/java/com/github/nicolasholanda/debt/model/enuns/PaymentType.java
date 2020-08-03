package com.github.nicolasholanda.debt.model.enuns;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum PaymentType {

    CASH(Integer.parseInt(Constants.CASH_CODE), Constants.CASH_DESCRIPTION),
    CREDIT(Integer.parseInt(Constants.CREDIT_CODE), Constants.CREDIT_DESCRIPTION);

    private Integer code;
    private String description;

    public static class Constants {
        public static final String CASH_CODE = "1";
        public static final String CREDIT_CODE = "2";
        public static final String CASH_DESCRIPTION = "Dinheiro";
        public static final String CREDIT_DESCRIPTION = "Crédito";
    }

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
