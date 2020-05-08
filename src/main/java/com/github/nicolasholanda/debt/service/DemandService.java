package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.dto.DemandListItemDTO;
import com.github.nicolasholanda.debt.model.dto.ExistentDemandDTO;
import com.github.nicolasholanda.debt.model.dto.NewDemandDTO;
import com.github.nicolasholanda.debt.model.mapper.DemandListItemMapper;
import com.github.nicolasholanda.debt.model.mapper.ExistentDemandMapper;
import com.github.nicolasholanda.debt.model.mapper.NewDemandMapper;
import com.github.nicolasholanda.debt.repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

import static com.github.nicolasholanda.debt.model.enuns.UserType.SELLER;
import static java.lang.String.format;

@Service
public class DemandService {

    private DemandRepository repository;
    private NewDemandMapper newDemandMapper;
    private ApplicationUserService userService;
    private DemandListItemMapper demandListItemMapper;
    private ExistentDemandMapper existentDemandMapper;

    @Autowired
    public DemandService(DemandRepository repository, NewDemandMapper newDemandMapper, ApplicationUserService userService,
                         DemandListItemMapper demandListItemMapper, ExistentDemandMapper existentDemandMapper) {
        this.repository = repository;
        this.userService = userService;
        this.newDemandMapper = newDemandMapper;
        this.demandListItemMapper = demandListItemMapper;
        this.existentDemandMapper = existentDemandMapper;
    }

    public Page<DemandListItemDTO> findPaginatedToCustomer(Integer page, Integer linesPerPage, Integer customerId) {
        var customer = userService.findById(customerId);
        if(customer.getUserType().equals(SELLER)) {
            throw new IllegalArgumentException("O usuário informado é um vendedor e, portanto, não possui pedidos.");
        }
        return repository.findAllByCustomerOrderByRequestDateDesc(customer, PageRequest.of(page, linesPerPage)).map(demandListItemMapper::toDTO);
    }

    public ExistentDemandDTO findById(Integer id) {
        return existentDemandMapper.toDTO(repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("O pedido de id %s não foi encontrado.", id));
        }));
    }

    @Transactional
    public Demand save(NewDemandDTO dto) {
        return repository.save(newDemandMapper.toModel(dto));
    }
}
