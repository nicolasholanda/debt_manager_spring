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
import javax.validation.Valid;

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
        return repository.findAll(filter).map(this::toDTO);
    }

    public Category findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("A categoria de id %s não foi encontrada.", id));
        });
    }

    public List<CategoryDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Category save(CategoryDTO dto) {
        return repository.save(toModel(dto));
    }

    public void update(@Valid CategoryDTO category) {
        var oldCategory = findById(category.getId());
        repository.save(updateData(oldCategory, toModel(category)));
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

    public Category toModel(CategoryDTO dto) {
        var category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
