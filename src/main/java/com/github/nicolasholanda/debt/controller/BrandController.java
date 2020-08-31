package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.Brand;
import com.github.nicolasholanda.debt.model.validation.UpdateModel;
import com.github.nicolasholanda.debt.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;

@Validated
@RestController
@RequestMapping(value = "/marcas")
public class BrandController {

    private BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public List<Brand> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Brand> findById(@PathVariable(value = "id") Integer id) {
        return ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Brand> save(@RequestBody Brand brand) {
        return created(URI.create(format("/marcas/%s", service.save(brand).getId()))).build();
    }

    @UpdateModel
    @PutMapping(path = "/{id}")
    public ResponseEntity<Brand> update(@PathVariable(value = "id") Integer id, @RequestBody Brand brand) {
        service.update(brand);
        return noContent().build();
    }
}
