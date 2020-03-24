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

    public Customer(String cpf, String name, String email) {
        super(cpf, name, email, UserType.CUSTOMER);
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }
}
