package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.nicolasholanda.debt.model.enuns.PaymentStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cash_payment")
public class CashPayment extends Payment {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date paymentDate;

    @NotNull(message = "{payment.duedate.notnull}")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dueDate;

    public CashPayment() {}

    public CashPayment(PaymentStatus status, BigDecimal value, Demand demand, Date dueDate, Date paymentDate) {
        super(status, value, demand);
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
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
}
