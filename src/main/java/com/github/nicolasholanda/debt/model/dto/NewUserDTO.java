package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.Address;
import com.github.nicolasholanda.debt.model.validation.NewUser;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NewUser
public class NewUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @CPF(message = "{user.cpf.valid}")
    private String cpf;

    @NotNull(message = "{user.name.notnull}")
    @Size(min = 2, max = 200, message = "{user.name.size}")
    private String name;

    @NotNull(message = "{user.email.notnull}")
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{user.email.pattern}")
    private String email;

    @NotNull(message = "{user.type.notnull}")
    private Integer userType;

    @NotNull(message = "{user.phonenumber.notnull}")
    @Size(min = 11, max = 11, message = "{user.phonenumber.size}")
    private String phoneNumber;

    public NewUserDTO() {
    }

    public NewUserDTO(String cpf, String name, String email, Integer userType, String phoneNumber) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
