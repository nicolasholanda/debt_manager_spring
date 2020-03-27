package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Category;
import com.github.nicolasholanda.debt.model.dto.CategoryDTO;
import com.github.nicolasholanda.debt.repository.CategoryRepository;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Page<CategoryDTO> findPaginated(Integer page, Integer linesPerPage, String direction, String orderBy) {
        var filter = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(filter).map(CategoryDTO::new);
    }

    public Category findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("A categoria de id %s não foi encontrada.", id));
        });
    }

    public List<CategoryDTO> findAll() {
        return repository.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public void update(Category category) {
        var oldCategory = findById(category.getId());
        save(updateData(oldCategory, category));
    }

    public void delete(Integer id) {
        Try.run(() -> repository.delete(findById(id))).getOrElseThrow(() -> {
            throw new DataIntegrityViolationException("Não é possível remover uma categoria que possui produtos.");
        });
    }

    private Category updateData(Category oldCategory, Category newCategory) {
        oldCategory.setName(newCategory.getName());
        return oldCategory;
    }
}
