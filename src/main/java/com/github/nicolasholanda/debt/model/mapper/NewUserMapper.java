package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.Customer;
import com.github.nicolasholanda.debt.model.Seller;
import com.github.nicolasholanda.debt.model.dto.NewUserDTO;
import com.github.nicolasholanda.debt.model.enuns.UserType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserTypeMapper.class)
public interface NewUserMapper {

    default ApplicationUser toModel(NewUserDTO dto) {
        if(dto.getUserType().equals(UserType.SELLER.getCode())) {
            return toSeller(dto);
        }
        return toCustomer(dto);
    }

    Seller toSeller(NewUserDTO dto);
    Customer toCustomer(NewUserDTO dto);
}
