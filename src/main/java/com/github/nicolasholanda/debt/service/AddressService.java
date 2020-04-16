package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Address;
import com.github.nicolasholanda.debt.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import static java.lang.String.format;

@Service
public class AddressService {

    private AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("O endereço de id %s não existe.", id));
        });
    }
}
