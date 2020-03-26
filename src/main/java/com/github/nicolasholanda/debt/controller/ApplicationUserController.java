package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.dto.ApplicationUserDTO;
import com.github.nicolasholanda.debt.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.noContent;

@Controller
@RequestMapping(path = "/usuarios")
public class ApplicationUserController {

    private ApplicationUserService service;

    @Autowired
    public ApplicationUserController(ApplicationUserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ApplicationUserDTO>> findPaginated(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                  @RequestParam(value = "order", defaultValue = "name") String orderBy,
                                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(value = "lines", defaultValue = "10") Integer linesPerPage) {
        return ok(service.findPaginated(page, linesPerPage, direction, orderBy));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApplicationUser> findById(@PathVariable Integer id) {
        return ok(service.findById(id));
    }

    @GetMapping(path = "/todos")
    public ResponseEntity<List<ApplicationUserDTO>> findAll() {
        return ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ApplicationUser> save(@Valid @RequestBody ApplicationUserDTO userDTO) {
        var user = ApplicationUserDTO.toModel(userDTO);
        return created(URI.create(format("/usuarios/%s", service.save(user).getId()))).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApplicationUser> update(@PathVariable(value = "id") Integer id,
                                                  @Valid @RequestBody ApplicationUserDTO userDTO) {
        if(!userDTO.getId().equals(id)) {
            throw new IllegalArgumentException("O id enviado não corresponde ao id do usuário.");
        }
        service.update(ApplicationUserDTO.toModel(userDTO));
        return noContent().build();
    }
}
