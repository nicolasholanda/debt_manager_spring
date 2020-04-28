package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.nicolasholanda.debt.model.enuns.PaymentStatus;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "installment")
public class Installment extends BaseEntity<Integer> {

    @Min(value = 0, message = "{payment.value.min")
    @NotNull(message = "{payment.value.notnull}")
    private BigDecimal value;

    private Integer sequence;

    @NotNull(message = "{payment.status.notnull}")
    private Integer status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date paymentDate;

    @NotNull(message = "{payment.duedate.notnull}")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private CashPayment payment;

    public Installment() {}

    public Installment(BigDecimal value, Integer sequence, Integer status, Date paymentDate, Date dueDate, CashPayment payment) {
        this.value = value;
        this.sequence = sequence;
        this.status = status;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
        this.payment = payment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CashPayment getPayment() {
        return payment;
    }

    public void setPayment(CashPayment payment) {
        this.payment = payment;
    }
}
