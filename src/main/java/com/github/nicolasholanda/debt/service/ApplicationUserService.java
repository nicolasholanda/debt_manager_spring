package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import static java.lang.String.format;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository repository;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository repository) {
        this.repository = repository;
    }

    public ApplicationUser findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("Não foi encontrado o usuário de id %s", id));
        });
    }
}
