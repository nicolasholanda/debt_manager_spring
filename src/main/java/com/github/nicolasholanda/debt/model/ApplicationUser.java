package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.nicolasholanda.debt.model.enuns.UserType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class ApplicationUser extends BaseEntity<Integer> {

    private String cpf;
    private String name;
    private String email;
    private Integer userType;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Address> addresses;

    @ElementCollection
    @CollectionTable(name = "phone_number")
    private Set<String> phoneNumbers;

    public ApplicationUser() {}

    public ApplicationUser(String cpf, String name, String email, UserType userType) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
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

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
