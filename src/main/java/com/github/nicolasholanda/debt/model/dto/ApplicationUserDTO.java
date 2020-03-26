package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import com.github.nicolasholanda.debt.model.Customer;
import com.github.nicolasholanda.debt.model.Seller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static io.vavr.API.*;

public class ApplicationUserDTO extends BaseDTO<Integer> {

    @Size(min = 11, max = 11, message = "{user.cpf.size}")
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

    public ApplicationUserDTO() {}

    public ApplicationUserDTO(Integer id, String cpf, String name, String email, Integer userType, String phoneNumber) {
        super(id);
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
    }

    public ApplicationUserDTO(ApplicationUser user) {
        this.setId(user.getId());
        this.cpf = user.getCpf();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userType = user.getUserType().getCode();
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

    public static ApplicationUserDTO fromModel(ApplicationUser user) {
        return new ApplicationUserDTO(user);
    }

    public static ApplicationUser toModel(ApplicationUserDTO dto) {
        return Match(dto.getUserType()).of(
                Case($(1), new Seller(dto.getId(), dto.getCpf(), dto.getName(), dto.getPhoneNumber(), dto.getEmail())),
                Case($(2), new Customer(dto.getId(), dto.getCpf(), dto.getName(), dto.getPhoneNumber(), dto.getEmail())),
                Case($(), e -> {
                    throw new IllegalArgumentException("Tipo de usuário desconhecido.");
                })
        );
    }
}
