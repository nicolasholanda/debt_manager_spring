package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.Category;
import com.github.nicolasholanda.debt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable(value = "id") Integer id) {
        return ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return created(URI.create(format("/categorias/%s", service.save(category).getId()))).build();
    }
}
