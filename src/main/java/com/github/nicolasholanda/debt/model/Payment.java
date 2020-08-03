package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.nicolasholanda.debt.model.enuns.PaymentStatus;
import com.github.nicolasholanda.debt.model.enuns.PaymentType;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "paymentType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CashPayment.class, name = PaymentType.Constants.CASH_CODE),
        @JsonSubTypes.Type(value = CreditPayment.class, name = PaymentType.Constants.CREDIT_CODE)
})
public abstract class Payment extends BaseEntity<Integer> {

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "demand_id")
    private Demand demand;

    private Integer paymentType;

    @JsonProperty(access = READ_ONLY)
    private Integer status;

    public Payment() {}

    public Payment(Demand demand, PaymentType paymentType, PaymentStatus status) {
        this.demand = demand;
        this.paymentType = paymentType.getCode();
        this.status = status.getCode();
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentType getPaymentType() {
        return PaymentType.of(paymentType);
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.of(status);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
