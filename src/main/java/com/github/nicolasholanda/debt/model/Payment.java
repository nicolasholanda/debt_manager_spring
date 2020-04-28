package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.nicolasholanda.debt.model.enuns.PaymentType;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment extends BaseEntity<Integer> {

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "demand_id")
    private Demand demand;

    private Integer paymentType;

    private Integer numberOfInstallments;

    public Payment() {}

    public Payment(Demand demand, Integer paymentType, Integer numberOfInstallments) {
        this.demand = demand;
        this.paymentType = paymentType;
        this.numberOfInstallments = numberOfInstallments;
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

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
