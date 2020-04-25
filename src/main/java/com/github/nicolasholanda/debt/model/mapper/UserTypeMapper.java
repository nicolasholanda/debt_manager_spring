package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.enuns.UserType;
import org.springframework.stereotype.Component;

@Component
public class UserTypeMapper {

    public Integer map(UserType type) {
        return type.getCode();
    }

    public UserType map(Integer code) {
        return UserType.of(code);
    }
}
