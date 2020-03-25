package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.Category;
import com.github.nicolasholanda.debt.model.dto.CategoryDTO;
import com.github.nicolasholanda.debt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findPaginated(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(value = "order", defaultValue = "name") String orderBy,
                                                        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                        @RequestParam(value = "lines", defaultValue = "10") Integer linesPerPage) {
        return ok(service.findPaginated(page, linesPerPage, direction, orderBy));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable(value = "id") Integer id) {
        return ok(service.findById(id));
    }

    @GetMapping(path = "/todos")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        var category = Category.fromDTO(categoryDTO);
        return created(URI.create(format("/categorias/%s", service.save(category).getId()))).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Category> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody CategoryDTO category) {
        if(!category.getId().equals(id)) {
            throw new IllegalArgumentException("O id enviado não corresponde ao id da categoria.");
        }
        service.update(Category.fromDTO(category));
        return noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Category> delete(@PathVariable(value = "id") Integer id) {
        service.delete(id);
        return noContent().build();
    }
}
