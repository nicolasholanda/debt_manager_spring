package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Product;
import com.github.nicolasholanda.debt.repository.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import static java.lang.String.format;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("O produto com o id %s não foi encontrado.", id));
        });
    }

    public Product save(Product product) {
        return repository.save(product);
    }
}
