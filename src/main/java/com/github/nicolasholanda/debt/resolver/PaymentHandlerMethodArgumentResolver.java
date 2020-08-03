package com.github.nicolasholanda.debt.resolver;

import com.github.nicolasholanda.debt.model.CashPayment;
import com.github.nicolasholanda.debt.model.CreditPayment;
import com.github.nicolasholanda.debt.model.Payment;
import com.github.nicolasholanda.debt.model.enuns.PaymentType;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static java.lang.String.format;

public final class PaymentHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Payment.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest request, WebDataBinderFactory webDataBinderFactory) throws Exception {
        var type = getParameter(request, "paymentType");

        if (PaymentType.of(Integer.parseInt(type)).equals(PaymentType.CREDIT)) {
            var installments = getParameter(request, "installments");
            var issuerId = getParameter(request, "issuer_id");
            var cardToken = getParameter(request, "token");
            var paymentMethodId = getParameter(request, "payment_method_id");
            return new CreditPayment(Integer.parseInt(installments), issuerId, cardToken, paymentMethodId);
        }

        return new CashPayment();
    }

    private String getParameter(NativeWebRequest request, String key) {
        var value = request.getParameter(key);
        if(value == null) {
            throw new IllegalArgumentException(format("O campo %s não foi enviado no formulário.", key));
        }
        return value;
    }
}
