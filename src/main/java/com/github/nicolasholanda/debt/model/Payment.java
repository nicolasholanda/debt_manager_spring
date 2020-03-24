package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.nicolasholanda.debt.model.enuns.PaymentStatus;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment extends BaseEntity<Integer> {

    @Min(value = 0, message = "{payment.value.min")
    @NotNull(message = "{payment.value.notnull}")
    private BigDecimal value;

    @NotNull(message = "{payment.status.notnull}")
    private Integer status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "demand_id")
    private Demand demand;

    public Payment() {}

    public Payment(PaymentStatus status, BigDecimal value, Demand demand) {
        this.status = status.getCode();
        this.value = value;
        this.demand = demand;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.of(status);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.getCode();
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Demand getDemand() {
        return demand;
    }
}
