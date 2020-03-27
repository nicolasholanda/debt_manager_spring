package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.Address;
import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.Customer;
import com.github.nicolasholanda.debt.model.Seller;
import com.github.nicolasholanda.debt.model.validation.NewUser;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static io.vavr.API.*;

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

    @NotNull(message = "{user.address.notnull}")
    private Address address;

    public NewUserDTO() {
    }

    public NewUserDTO(String cpf, String name, String email, Integer userType, String phoneNumber, Address address) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static ApplicationUser toModel(NewUserDTO dto) {
        return Match(dto.getUserType()).of(
                Case($(1), new Seller(dto.getCpf(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getAddress())),
                Case($(2), new Customer(dto.getCpf(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getAddress())),
                Case($(), e -> {
                    throw new IllegalArgumentException("Tipo de usuário desconhecido.");
                })
        );
    }
}
