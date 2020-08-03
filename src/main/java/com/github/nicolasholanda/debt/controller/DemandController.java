package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.Payment;
import com.github.nicolasholanda.debt.model.dto.ExistentDemandDTO;
import com.github.nicolasholanda.debt.model.dto.NewDemandDTO;
import com.github.nicolasholanda.debt.model.mapper.ExistentDemandMapper;
import com.github.nicolasholanda.debt.model.mapper.NewDemandMapper;
import com.github.nicolasholanda.debt.model.validation.DemandValidator;
import com.github.nicolasholanda.debt.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/pedidos")
public class DemandController {

    private DemandService service;
    private DemandValidator demandValidator;
    private NewDemandMapper newDemandMapper;
    private ExistentDemandMapper existentDemandMapper;

    @Autowired
    public DemandController(DemandService service, DemandValidator demandValidator, NewDemandMapper newDemandMapper, ExistentDemandMapper existentDemandMapper) {
        this.service = service;
        this.demandValidator = demandValidator;
        this.newDemandMapper = newDemandMapper;
        this.existentDemandMapper = existentDemandMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExistentDemandDTO> findById(@PathVariable(value = "id") Integer id) {
        return ok(existentDemandMapper.toDTO(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Demand> save(@RequestBody NewDemandDTO dto) {
        return created(URI.create(format("/pedidos/%s", service.save(newDemandMapper.toModel(dto)).getId()))).build();
    }

    @PostMapping(path = "/{id}/processar-pagamento", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Demand> processPayment(@PathVariable("id") Integer id, Payment payment) {
        var demand = service.findById(id);
        var validation = demandValidator.validToPay(demand);
        if(validation.isInvalid()) {
            throw new IllegalArgumentException(validation.getError());
        }
        return created(URI.create(format("/pagamentos/%s", service.processPaymentForDemand(demand, payment).getId()))).build();
    }
}
