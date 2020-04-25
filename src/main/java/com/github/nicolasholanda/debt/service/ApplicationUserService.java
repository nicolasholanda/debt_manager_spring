package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.dto.ExistentUserDTO;
import com.github.nicolasholanda.debt.model.dto.NewUserDTO;
import com.github.nicolasholanda.debt.model.mapper.ExistentUserMapper;
import com.github.nicolasholanda.debt.model.mapper.NewUserMapper;
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

import static java.lang.String.format;

@Service
public class ApplicationUserService {

    private NewUserMapper newUserMapper;
    private ApplicationUserRepository repository;
    private ExistentUserMapper existentUserMapper;

    @Autowired
    public ApplicationUserService(NewUserMapper newUserMapper, ApplicationUserRepository repository, ExistentUserMapper existentUserMapper) {
        this.newUserMapper = newUserMapper;
        this.repository = repository;
        this.existentUserMapper = existentUserMapper;
    }

    public ApplicationUser findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("Não foi encontrado o usuário de id %s", id));
        });
    }

    public Page<ExistentUserDTO> findPaginated(Integer page, Integer linesPerPage, String direction, String orderBy) {
        var filter = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(filter).map(existentUserMapper::toDTO);
    }

    public List<ExistentUserDTO> findAll() {
        return repository.findAll().stream().map(existentUserMapper::toDTO).collect(Collectors.toList());
    }

    public ApplicationUser save(@Valid NewUserDTO user) {
        return repository.save(newUserMapper.toModel(user));
    }

    public void update(@Valid ExistentUserDTO user) {
        var oldUser = findById(user.getId());
        repository.save(existentUserMapper.updateUser(user, oldUser));
    }
}
