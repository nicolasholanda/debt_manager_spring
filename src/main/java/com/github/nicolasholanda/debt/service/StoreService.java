package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Seller;
import com.github.nicolasholanda.debt.model.Store;
import com.github.nicolasholanda.debt.model.dto.NewStoreDTO;
import com.github.nicolasholanda.debt.model.dto.StoreListItemDTO;
import com.github.nicolasholanda.debt.repository.StoreRepository;
import com.github.nicolasholanda.debt.utils.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.nicolasholanda.debt.model.enuns.UserType.SELLER;
import static java.lang.String.format;

@Service
public class StoreService {

    private StoreRepository repository;
    private AddressService addressService;
    private ApplicationUserService userService;

    @Autowired
    public StoreService(StoreRepository repository, AddressService addressService, ApplicationUserService userRepository) {
        this.repository = repository;
        this.userService = userRepository;
        this.addressService = addressService;
    }

    public Store findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("A loja de id %s não existe.", id));
        });
    }

    public Store save(NewStoreDTO store) {
        return repository.save(toModel(store));
    }

    public Page<StoreListItemDTO> findAllWithDistance(PageRequest filter, String query, Integer addressId,
                                                      List<Integer> brandList) {
        return repository.findPaginated(query, brandList, filter).map(s -> {
            var dto = fromModel(s);
            var deliveryAddress = addressService.findById(addressId);
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

    private Page<StoreListItemDTO> orderByDistance(Page<StoreListItemDTO> page, String direction) {
        var listResult = new ArrayList<>(page.toList());
        Collections.sort(listResult);
        if (direction.equals("DESC")) {
            Collections.reverse(listResult);
        }
        return listToPage(listResult, page);
    }

    private Page<StoreListItemDTO> filterByRadius(Page<StoreListItemDTO> page, Integer radius) {
        var listResult = new ArrayList<>(page.toList()).stream().filter(s -> s.getDistance().intValue() <= radius).collect(Collectors.toList());
        return listToPage(listResult, page);
    }

    private Page<StoreListItemDTO> listToPage(List<StoreListItemDTO> list, Page<StoreListItemDTO> page) {
        var pageable = page.getPageable();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    public StoreListItemDTO fromModel(Store store) {
        return new StoreListItemDTO(store);
    }

    public Store toModel(NewStoreDTO dto) {
        var user = userService.findById(dto.getOwnerId());
        if(!user.getUserType().equals(SELLER)) {
            throw new IllegalArgumentException("O dono da loja deve ser um usuário vendedor.");
        } else if(!dto.getAddress().getUser().getId().equals(dto.getOwnerId())) {
            throw new IllegalArgumentException("O endereço da loja deve ter sido cadastrado pelo vendedor.");
        }
        var store = new Store();
        store.setName(dto.getName());
        store.setOwner(new Seller(user));
        store.setAddress(dto.getAddress());
        store.setBrands(dto.getBrands());
        return store;
    }
}
