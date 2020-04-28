package com.github.nicolasholanda.debt.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cash_payment")
public class CashPayment extends Payment {

    @OneToMany(mappedBy = "payment")
    private List<Installment> installments;

    public CashPayment() {}

    public CashPayment(Demand demand) {
        super(demand);
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }
}
