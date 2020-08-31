package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Category;
import com.github.nicolasholanda.debt.model.dto.CategoryDTO;
import com.github.nicolasholanda.debt.model.mapper.CategoryMapper;
import com.github.nicolasholanda.debt.repository.CategoryRepository;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class CategoryService {

    private CategoryMapper mapper;
    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryMapper mapper, CategoryRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<CategoryDTO> findPaginated(Integer page, Integer linesPerPage, String direction, String orderBy) {
        var filter = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(filter).map(mapper::toDTO);
    }

    public Category findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("A categoria de id %s não foi encontrada.", id));
        });
    }

    public List<CategoryDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public Category save(CategoryDTO dto) {
        return repository.save(mapper.toModel(dto));
    }

    public void update(@Valid CategoryDTO category) {
        var oldCategory = findById(category.getId());
        repository.save(updateData(oldCategory, mapper.toModel(category)));
    }

    public void delete(Integer id) {
        var category = findById(id);
        Try.run(() -> repository.delete(category)).getOrElseThrow(() -> {
            throw new DataIntegrityViolationException("Não é possível remover uma categoria que possui produtos.");
        });
    }

    private Category updateData(Category oldCategory, Category newCategory) {
        oldCategory.setName(newCategory.getName());
        return oldCategory;
    }
}
