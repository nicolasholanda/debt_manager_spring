package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.CreditPayment;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class MercadoPagoPaymentMapper {

    @AfterMapping
    protected void setPayer(CreditPayment creditPayment, @MappingTarget Payment mercadoPagoPayment) {
        var customer = creditPayment.getDemand().getCustomer();
        mercadoPagoPayment.setPayer(new Payer()
                .setEmail(customer.getEmail())
                .setIdentification(new Identification().setType("CPF").setNumber(customer.getCpf()))
        );
    }

    @Mapping(target = "status", ignore = true)
    @Mapping(source = "cardToken", target = "token")
    @Mapping(target = "transactionAmount", expression = "java(creditPayment.getDemand().getTotalPrice().floatValue())")
    public abstract Payment toMercadoPagoPayment(CreditPayment creditPayment);
}
