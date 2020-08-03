package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.Payment;
import com.github.nicolasholanda.debt.repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

import java.util.Map;

import static com.github.nicolasholanda.debt.model.enuns.UserType.SELLER;
import static java.lang.String.format;

@Service
public class DemandService {

    private DemandRepository repository;
    private ApplicationUserService userService;
    private Map<String, PaymentService> paymentServiceMap;

    @Autowired
    public DemandService(DemandRepository repository, ApplicationUserService userService,
                         Map<String, PaymentService> paymentServiceMap) {
        this.repository = repository;
        this.userService = userService;
        this.paymentServiceMap = paymentServiceMap;
    }

    public Page<Demand> findPaginatedToCustomer(Integer page, Integer linesPerPage, Integer customerId) {
        var customer = userService.findById(customerId);
        if(customer.getUserType().equals(SELLER)) {
            throw new IllegalArgumentException("O usuário informado é um vendedor e, portanto, não possui pedidos.");
        }
        return repository.findAllByCustomerOrderByRequestDateDesc(customer, PageRequest.of(page, linesPerPage));
    }

    public Demand findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("O pedido de id %s não foi encontrado.", id));
        });
    }

    @Transactional
    public Payment processPaymentForDemand(Demand demand, Payment payment) {
        payment.setDemand(demand);
        var savedPayment = paymentServiceMap.get(payment.getPaymentType().getCode().toString()).savePayment(payment);
        demand.setPayment(savedPayment);
        return savedPayment;
    }

    @Transactional
    public Demand save(Demand demand) {
        return repository.save(demand);
    }
}
