package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.nicolasholanda.debt.model.enuns.PaymentStatus;
import com.github.nicolasholanda.debt.model.enuns.PaymentType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "credit_payment")
@JsonTypeName(PaymentType.Constants.CREDIT_CODE)
public class CreditPayment extends Payment {

    @JsonProperty("installments")
    private Integer installments;

    @Transient
    @JsonProperty(value = "issuer_id", access = JsonProperty.Access.WRITE_ONLY)
    private String issuerId;

    @Transient
    @JsonProperty(value = "card_token", access = JsonProperty.Access.WRITE_ONLY)
    private String cardToken;

    @Transient
    @JsonProperty(value = "payment_method_id", access = JsonProperty.Access.WRITE_ONLY)
    private String paymentMethodId;

    @JsonIgnore
    private String mercadoPagoId;

    public CreditPayment() {}

    public CreditPayment(Demand demand, PaymentStatus status) {
        super(demand, PaymentType.CREDIT, status);
    }

    public CreditPayment(Integer installments, String issuerId, String cardToken, String paymentMethodId) {
        this.issuerId = issuerId;
        this.cardToken = cardToken;
        this.installments = installments;
        this.paymentMethodId = paymentMethodId;
        this.setPaymentType(PaymentType.CREDIT.getCode());
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getMercadoPagoId() {
        return mercadoPagoId;
    }

    public void setMercadoPagoId(String mercadoPagoId) {
        this.mercadoPagoId = mercadoPagoId;
    }
}
