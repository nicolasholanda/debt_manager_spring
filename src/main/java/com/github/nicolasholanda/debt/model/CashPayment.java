package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.nicolasholanda.debt.model.enuns.PaymentStatus;
import com.github.nicolasholanda.debt.model.enuns.PaymentType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cash_payment")
@JsonTypeName(PaymentType.Constants.CASH_CODE)
public class CashPayment extends Payment {

    public CashPayment() {}

    public CashPayment(Demand demand, PaymentStatus status) {
        super(demand, PaymentType.CASH, status);
    }
}
