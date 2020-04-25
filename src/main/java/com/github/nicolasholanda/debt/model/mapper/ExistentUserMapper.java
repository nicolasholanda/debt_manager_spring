package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.Customer;
import com.github.nicolasholanda.debt.model.Seller;
import com.github.nicolasholanda.debt.model.dto.ExistentUserDTO;
import com.github.nicolasholanda.debt.model.enuns.UserType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = UserTypeMapper.class)
public interface ExistentUserMapper {

    ExistentUserDTO toDTO(ApplicationUser user);

    @Mapping(target = "userType", ignore = true)
    ApplicationUser updateUser(ExistentUserDTO userDTO, @MappingTarget ApplicationUser user);
}