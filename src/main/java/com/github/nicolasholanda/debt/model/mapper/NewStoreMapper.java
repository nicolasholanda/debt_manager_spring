package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.Seller;
import com.github.nicolasholanda.debt.model.Store;
import com.github.nicolasholanda.debt.model.dto.NewStoreDTO;
import com.github.nicolasholanda.debt.service.AddressService;
import com.github.nicolasholanda.debt.service.ApplicationUserService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.nicolasholanda.debt.model.enuns.UserType.SELLER;

@Mapper(componentModel = "spring")
public abstract class NewStoreMapper {

    @Autowired
    protected ApplicationUserService userService;

    @Autowired
    protected AddressService addressService;

    @AfterMapping
    protected void setOwner(NewStoreDTO dto, @MappingTarget Store store) {
        var address = addressService.findById(dto.getAddressId());
        if(!address.getUser().getUserType().equals(SELLER)) {
            throw new IllegalArgumentException("O dono da loja deve ser um usuário vendedor.");
        } else if(!address.getUser().getId().equals(dto.getOwnerId())) {
            throw new IllegalArgumentException("O endereço da loja deve ter sido cadastrado pelo vendedor.");
        }
        store.setAddress(address);
        store.setOwner((Seller) address.getUser());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "demands", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "ratingList", ignore = true)
    @Mapping(target = "storeItems", ignore = true)
    @Mapping(target = "ratingAverage", ignore = true)
    public abstract Store toModel(NewStoreDTO dto);
}
