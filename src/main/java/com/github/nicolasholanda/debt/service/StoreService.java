package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Store;
import com.github.nicolasholanda.debt.model.dto.NewStoreDTO;
import com.github.nicolasholanda.debt.model.dto.StoreListItemDTO;
import com.github.nicolasholanda.debt.model.mapper.NewStoreMapper;
import com.github.nicolasholanda.debt.model.mapper.StoreListItemMapper;
import com.github.nicolasholanda.debt.repository.StoreRepository;
import com.github.nicolasholanda.debt.utils.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import java.util.List;

import static com.github.nicolasholanda.debt.utils.ServiceUtils.filterByRadius;
import static com.github.nicolasholanda.debt.utils.ServiceUtils.orderByDistance;
import static java.lang.String.format;

@Service
public class StoreService {

    private StoreRepository repository;
    private AddressService addressService;
    private NewStoreMapper newStoreMapper;
    private StoreListItemMapper storeListItemMapper;

    @Autowired
    public StoreService(StoreRepository repository, AddressService addressService, NewStoreMapper newStoreMapper, StoreListItemMapper storeListItemMapper) {
        this.repository = repository;
        this.addressService = addressService;
        this.newStoreMapper = newStoreMapper;
        this.storeListItemMapper = storeListItemMapper;
    }

    public Store findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("A loja de id %s não existe.", id));
        });
    }

    public Store save(NewStoreDTO dto) {
        return repository.save(newStoreMapper.toModel(dto));
    }

    public Page<StoreListItemDTO> findAllWithDistance(PageRequest filter, String query, Integer addressId,
                                                      List<Integer> brandList) {
        var deliveryAddress = addressService.findById(addressId);
        return repository.findPaginated(query, brandList, filter).map(s -> {
            var dto = storeListItemMapper.toDTO(s);
            dto.setDistance(AddressUtils.distanceInKm(s.getAddress(), deliveryAddress));
            return dto;
        });
    }

    public Page<StoreListItemDTO> findPaginated(Integer page, Integer linesPerPage, String direction, String orderBy,
                                                String query, Integer addressId, Integer radius, List<Integer> brandList) {
        if(orderBy.equals("distance")) {
            var filter = PageRequest.of(page, linesPerPage);
            return orderByDistance(filterByRadius(findAllWithDistance(filter, query, addressId, brandList), radius), direction);
        }
        var filter = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return filterByRadius(findAllWithDistance(filter, query, addressId, brandList), radius);
    }
}
