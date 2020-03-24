package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Brand;
import com.github.nicolasholanda.debt.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

import static java.lang.String.format;

@Service
public class BrandService {

    private BrandRepository repository;

    @Autowired
    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public List<Brand> findAll() {
        return repository.findAll();
    }

    public Brand findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("A marca de id %s não existe.", id));
        });
    }

    public Brand save(Brand brand) {
        return repository.save(brand);
    }

    public void update(Brand brand) {
        findById(brand.getId());
        save(brand);
    }
}
