package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.Store;
import com.github.nicolasholanda.debt.model.dto.NewStoreDTO;
import com.github.nicolasholanda.debt.model.dto.ProductListItemDTO;
import com.github.nicolasholanda.debt.model.dto.StoreListItemDTO;
import com.github.nicolasholanda.debt.model.enuns.Gender;
import com.github.nicolasholanda.debt.service.ProductService;
import com.github.nicolasholanda.debt.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/lojas")
public class StoreController {

    private StoreService service;
    private ProductService productService;

    @Autowired
    public StoreController(StoreService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<StoreListItemDTO>> findPaginated(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(value = "order", defaultValue = "name") String orderBy,
                                                                @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                                @RequestParam(value = "lines", defaultValue = "10") Integer linesPerPage,
                                                                @RequestParam(value = "query", defaultValue = "") String query,
                                                                @RequestParam(value = "address_id") Integer addressId,
                                                                @RequestParam(value = "radius", defaultValue = "27") Integer radius,
                                                                @RequestParam(value = "brands", defaultValue = "") String brands) {
        var brandList = brands.isEmpty() ? new ArrayList<Integer>() :
                List.of(brands.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return ok(service.findPaginated(page, linesPerPage, direction, orderBy, query, addressId, radius, brandList));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Store> findById(@PathVariable(value = "id") Integer id) {
        return ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Store> save(@RequestBody NewStoreDTO storeDTO) {
        return created(URI.create(format("/lojas/%s", service.save(storeDTO).getId()))).build();
    }

    @GetMapping(path = "/{id}/produtos")
    public ResponseEntity<Page<ProductListItemDTO>> findProductsPaginated(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                          @RequestParam(value = "lines", defaultValue = "10") Integer linesPerPage,
                                                                          @RequestParam(value = "brands", defaultValue = "") String brands,
                                                                          @RequestParam(value = "price", defaultValue = "9999") BigDecimal maxPrice,
                                                                          @RequestParam(value = "store") Integer storeId,
                                                                          @RequestParam(value = "genders", defaultValue = "1,2,3") String genders,
                                                                          @RequestParam(value = "categories", defaultValue = "") String categories) {
        var brandList = brands.isEmpty() ? new ArrayList<Integer>() :
                List.of(brands.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        var genderList = genders.isEmpty() ? new ArrayList<Integer>() :
                List.of(genders.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        var categoryList = categories.isEmpty() ? new ArrayList<Integer>() :
                List.of(categories.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return ok(productService.findPaginatedToStore(page, linesPerPage, maxPrice, storeId, brandList, genderList, categoryList));
    }
}
