package com.github.nicolasholanda.debt.model;

import com.github.nicolasholanda.debt.model.enuns.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "application_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ApplicationUser extends BaseEntity<Integer> {

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

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @NotNull(message = "{user.phonenumber.notnull}")
    @Size(min = 11, max = 11, message = "{user.phonenumber.size}")
    private String phoneNumber;

    public ApplicationUser() {}

    public ApplicationUser(String cpf, String name, String email, String phoneNumber, UserType userType) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType.getCode();
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

    public UserType getUserType() {
        return UserType.of(userType);
    }

    public void setUserType(UserType userType) {
        this.userType = userType.getCode();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
