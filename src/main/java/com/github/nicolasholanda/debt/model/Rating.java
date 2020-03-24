package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.nicolasholanda.debt.model.BaseEntity;
import com.github.nicolasholanda.debt.model.Customer;
import com.github.nicolasholanda.debt.model.Store;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rating")
public class Rating extends BaseEntity<Integer> {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "store_id")
    @NotNull(message = "{rating.store.notnull}")
    private Store store;

    @NotNull(message = "{rating.comment.notnull}")
    @Size(min = 5, max = 1200, message = "{rating.comment.size}")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "{rating.user.notnull}")
    private Customer user;

    public Rating() {
    }

    public Rating(Store store, String comment, Customer user) {
        this.store = store;
        this.comment = comment;
        this.user = user;
    }

    @JsonIgnore
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}
