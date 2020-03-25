package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Category;
import com.github.nicolasholanda.debt.repository.CategoryRepository;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import static java.lang.String.format;

@Service
public class CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("A categoria de id %s não foi encontrada.", id));
        });
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public void update(Category category) {
        findById(category.getId());
        save(category);
    }

    public void delete(Integer id) {
        Try.run(() -> repository.delete(findById(id))).getOrElseThrow(() -> {
            throw new DataIntegrityViolationException("Não é possível remover uma categoria que possui produtos.");
        });
    }

}
