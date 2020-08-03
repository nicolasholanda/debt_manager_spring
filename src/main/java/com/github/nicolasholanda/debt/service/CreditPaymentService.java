package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.CreditPayment;
import com.github.nicolasholanda.debt.model.Payment;
import com.github.nicolasholanda.debt.model.enuns.PaymentType;
import com.github.nicolasholanda.debt.model.mapper.MercadoPagoPaymentMapper;
import com.github.nicolasholanda.debt.repository.PaymentRepository;
import com.mercadopago.MercadoPago;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.github.nicolasholanda.debt.model.enuns.PaymentStatus.IN_PROCESS;
import static com.github.nicolasholanda.debt.model.enuns.PaymentStatus.PAID;
import static com.mercadopago.resources.Payment.Status.approved;
import static com.mercadopago.resources.Payment.Status.rejected;
import static java.lang.String.format;

@Service(value = PaymentType.Constants.CREDIT_CODE)
public class CreditPaymentService implements PaymentService {

    private PaymentRepository repository;
    private MercadoPagoPaymentMapper mercadoPagoPaymentMapper;
    private static final String BASE_MESSAGE = "Erro ao processar o pagamento. Por favor,";

    @Autowired
    public CreditPaymentService(PaymentRepository repository, MercadoPagoPaymentMapper mercadoPagoPaymentMapper) {
        this.repository = repository;
        this.mercadoPagoPaymentMapper = mercadoPagoPaymentMapper;
    }

    @PostConstruct
    public void init() {
        Try.run(() -> MercadoPago.SDK.setAccessToken(System.getenv("MP_ACCESS_TOKEN"))).onFailure(e -> {
            throw new IllegalArgumentException("Não foi possível configurar o Mercado Pago.");
        });
    }

    @Override
    public Payment savePayment(Payment payment) {
        var mercadoPagoPayment = mercadoPagoPaymentMapper.toMercadoPagoPayment((CreditPayment) payment);
        Try.run(mercadoPagoPayment::save).onFailure(e -> {
            throw new IllegalArgumentException(format("%s tente novamente mais tarde.", BASE_MESSAGE));
        });
        var status = Option.of(mercadoPagoPayment.getStatus()).getOrElseThrow(() -> {
            throw new IllegalArgumentException(format("%s entre em contato com o suporte.", BASE_MESSAGE));
        });
        if(status.equals(rejected)) {
            throw new IllegalArgumentException(format("%s confira os dados do cartão e se há limite disponível.", BASE_MESSAGE));
        } else if (status.equals(approved)) {
            payment.setStatus(PAID.getCode());
        } else {
            payment.setStatus(IN_PROCESS.getCode());
        }
        ((CreditPayment) payment).setMercadoPagoId(mercadoPagoPayment.getId());
        return repository.save(payment);
    }
}
