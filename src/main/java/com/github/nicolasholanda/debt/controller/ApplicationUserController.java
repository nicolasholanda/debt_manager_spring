package com.github.nicolasholanda.debt.controller;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping(path = "/usuarios")
public class ApplicationUserController {

    private ApplicationUserService service;

    @Autowired
    public ApplicationUserController(ApplicationUserService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApplicationUser> findById(@PathVariable Integer id) {
        return ok(service.findById(id));
    }
}
