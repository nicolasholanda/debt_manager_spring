package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.Product;
import com.github.nicolasholanda.debt.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/produtos")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Integer id) {
        return ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return created(URI.create(format("/produtos/%s", service.save(product).getId()))).build();
    }

}
