package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.dto.ExistentUserDTO;
import com.github.nicolasholanda.debt.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findAll(filter).map(ExistentUserDTO::new);
    }

    public List<ExistentUserDTO> findAll() {
        return repository.findAll().stream().map(ExistentUserDTO::new).collect(Collectors.toList());
    }

    public ApplicationUser save(ApplicationUser user) {
        return repository.save(user);
    }

    public void update(ApplicationUser user) {
        var oldUser = findById(user.getId());
        repository.save(updateData(oldUser, user));
    }

    private ApplicationUser updateData(ApplicationUser oldUser, ApplicationUser newUser) {
        oldUser.setCpf(newUser.getCpf());
        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        return oldUser;
    }
}
