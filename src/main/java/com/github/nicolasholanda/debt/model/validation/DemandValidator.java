package com.github.nicolasholanda.debt.model.validation;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.enuns.PaymentStatus;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

import static com.github.nicolasholanda.debt.model.enuns.DemandStatus.ACCEPTED;

@Component("demandValidator")
public class DemandValidator {
    public Validation<String, Demand> validToPay(Demand demand) {
        if(!demand.getStatus().equals(ACCEPTED)) {
            return Validation.invalid("Não é possível pagar um pedido que não foi aceito.");
        } else if (demand.getPayment() != null && demand.getPayment().getStatus().equals(PaymentStatus.PAID)) {
            return Validation.invalid("O pedido já foi pago.");
        }
        return Validation.valid(demand);
    }
}
