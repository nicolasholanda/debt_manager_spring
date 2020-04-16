package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.Customer;
import com.github.nicolasholanda.debt.model.Seller;
import com.github.nicolasholanda.debt.model.dto.ExistentUserDTO;
import com.github.nicolasholanda.debt.model.dto.NewUserDTO;
import com.github.nicolasholanda.debt.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static java.lang.String.format;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository repository;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository repository) {
        this.repository = repository;
    }

    public ApplicationUser findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("Não foi encontrado o usuário de id %s", id));
        });
    }

    public Page<ExistentUserDTO> findPaginated(Integer page, Integer linesPerPage, String direction, String orderBy) {
        var filter = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(filter).map(this::fromModel);
    }

    public List<ExistentUserDTO> findAll() {
        return repository.findAll().stream().map(this::fromModel).collect(Collectors.toList());
    }

    public ApplicationUser save(@Valid NewUserDTO user) {
        return repository.save(toModel(user));
    }

    public void update(@Valid ExistentUserDTO user) {
        var oldUser = findById(user.getId());
        repository.save(updateData(oldUser, toModel(user)));
    }

    private ApplicationUser updateData(ApplicationUser oldUser, ApplicationUser newUser) {
        oldUser.setCpf(newUser.getCpf());
        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        return oldUser;
    }

    public static ApplicationUser toModel(NewUserDTO dto) {
        return Match(dto.getUserType()).of(
                Case($(1), new Seller(dto.getCpf(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getAddress())),
                Case($(2), new Customer(dto.getCpf(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getAddress())),
                Case($(), e -> {
                    throw new IllegalArgumentException("Tipo de usuário desconhecido.");
                })
        );
    }

    public ApplicationUser toModel(ExistentUserDTO dto) {
        return Match(dto.getUserType()).of(
                Case($(1), new Seller(dto.getId(), dto.getCpf(), dto.getName(), dto.getPhoneNumber(), dto.getEmail())),
                Case($(2), new Customer(dto.getId(), dto.getCpf(), dto.getName(), dto.getPhoneNumber(), dto.getEmail())),
                Case($(), e -> {
                    throw new IllegalArgumentException("Tipo de usuário desconhecido.");
                })
        );
    }

    public ExistentUserDTO fromModel(ApplicationUser user) {
        return new ExistentUserDTO(user);
    }
}
