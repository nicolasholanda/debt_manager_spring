package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.nicolasholanda.debt.model.enuns.UserType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
@Table(name = "customer")
public class Customer extends ApplicationUser {

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Demand> demands = emptyList();

    public Customer() {}

    public Customer(Integer id, String cpf, String name, String phoneNumber, String email) {
        super(cpf, name, email, phoneNumber, UserType.CUSTOMER);
        this.setId(id);
    }

    public Customer(String cpf, String name, String phoneNumber, String email, Address address) {
        super(cpf, name, email, phoneNumber, UserType.SELLER, List.of(address));
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }
}
