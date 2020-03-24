package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import static java.lang.String.format;

@Service
public class DemandService {

    private DemandRepository repository;

    @Autowired
    public DemandService(DemandRepository repository) {
        this.repository = repository;
    }

    public Demand findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("O pedido de id %s não foi encontrado.", id));
        });
    }
}
