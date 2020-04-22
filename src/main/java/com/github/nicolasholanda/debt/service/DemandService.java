package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.dto.DemandListItemDTO;
import com.github.nicolasholanda.debt.model.dto.ExistentDemandDTO;
import com.github.nicolasholanda.debt.model.enuns.UserType;
import com.github.nicolasholanda.debt.repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import static com.github.nicolasholanda.debt.model.enuns.UserType.SELLER;
import static java.lang.String.format;

@Service
public class DemandService {

    private DemandRepository repository;
    private ApplicationUserService userService;

    @Autowired
    public DemandService(DemandRepository repository, ApplicationUserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Page<DemandListItemDTO> findPaginatedToCustomer(Integer page, Integer linesPerPage, Integer customerId) {
        var customer = userService.findById(customerId);
        if(customer.getUserType().equals(SELLER)) {
            throw new IllegalArgumentException("O usuário informado é um vendedor e, portanto, não possui pedidos.");
        }
        return repository.findAllByCustomerOrderByRequestDateDesc(customer, PageRequest.of(page, linesPerPage)).map(this::toListItemDTO);
    }

    public ExistentDemandDTO findById(Integer id) {
        return toDTO(repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("O pedido de id %s não foi encontrado.", id));
        }));
    }

    public ExistentDemandDTO toDTO(Demand demand) {
        return new ExistentDemandDTO(demand);
    }

    public DemandListItemDTO toListItemDTO(Demand demand) {
        return new DemandListItemDTO(demand);
    }
}
