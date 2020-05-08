package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.dto.ExistentDemandDTO;
import com.github.nicolasholanda.debt.model.dto.NewDemandDTO;
import com.github.nicolasholanda.debt.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public DemandController(DemandService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExistentDemandDTO> findById(@PathVariable(value = "id") Integer id) {
        return ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Demand> save(@RequestBody NewDemandDTO dto) {
        return created(URI.create(format("/pedidos/%s", service.save(dto).getId()))).build();
    }
}
