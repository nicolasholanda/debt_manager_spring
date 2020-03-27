package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.nicolasholanda.debt.model.enuns.UserType;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "seller")
public class Seller extends ApplicationUser {

    @JsonIgnore
    @OneToOne(mappedBy = "owner")
    private Store store;

    public Seller() {}

    public Seller(Integer id, String cpf, String name, String phoneNumber, String email) {
        super(cpf, name, email, phoneNumber, UserType.SELLER);
        this.setId(id);
    }

    public Seller(String cpf, String name, String phoneNumber, String email, Address address) {
        super(cpf, name, email, phoneNumber, UserType.SELLER, List.of(address));
    }

    public Seller(String cpf, String name, String phoneNumber, String email, Store store) {
        super(cpf, name, email, phoneNumber, UserType.SELLER);
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
