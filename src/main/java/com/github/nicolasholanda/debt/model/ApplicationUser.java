package com.github.nicolasholanda.debt.model;

import com.github.nicolasholanda.debt.model.enuns.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "application_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ApplicationUser extends BaseEntity<Integer> {

    private String cpf;

    private String name;

    private String email;

    private Integer userType;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    private String phoneNumber;

    public ApplicationUser() {}

    public ApplicationUser(Integer id, String cpf, String name, String email, String phoneNumber, UserType userType, List<Address> addresses) {
        this.setId(id);
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.addresses = addresses;
        this.phoneNumber = phoneNumber;
        this.userType = userType.getCode();
    }

    public ApplicationUser(String cpf, String name, String email, String phoneNumber, UserType userType) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType.getCode();
    }

    public ApplicationUser(String cpf, String name, String email, String phoneNumber, UserType userType, List<Address> addresses) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType.getCode();
        this.addresses = addresses;
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
