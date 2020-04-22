package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.dto.DemandListItemDTO;
import com.github.nicolasholanda.debt.model.dto.ExistentUserDTO;
import com.github.nicolasholanda.debt.model.dto.NewUserDTO;
import com.github.nicolasholanda.debt.service.ApplicationUserService;
import com.github.nicolasholanda.debt.service.DemandService;
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
    private DemandService demandService;

    @Autowired
    public ApplicationUserController(ApplicationUserService service, DemandService demandService) {
        this.service = service;
        this.demandService = demandService;
    }

    @GetMapping
    public ResponseEntity<Page<ExistentUserDTO>> findPaginated(@RequestParam(value = "page", defaultValue = "0") Integer page,
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
    public ResponseEntity<List<ExistentUserDTO>> findAll() {
        return ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ApplicationUser> save(@Valid @RequestBody NewUserDTO userDTO) {
        return created(URI.create(format("/usuarios/%s", service.save(userDTO).getId()))).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApplicationUser> update(@PathVariable(value = "id") Integer id,
                                                  @Valid @RequestBody ExistentUserDTO userDTO) {
        if(!userDTO.getId().equals(id)) {
            throw new IllegalArgumentException("O id enviado não corresponde ao id do usuário.");
        }
        service.update(userDTO);
        return noContent().build();
    }

    @GetMapping(path = "clientes/{id}/pedidos")
    public ResponseEntity<Page<DemandListItemDTO>> findOrders(@PathVariable(value = "id") Integer id,
                                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "lines", defaultValue = "10") Integer linesPerPage) {
        return ok(demandService.findPaginatedToCustomer(page, linesPerPage, id));
    }
}
