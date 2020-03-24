package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "store")
public class Store extends BaseEntity<Integer> {

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "owner_id")
    @NotNull(message = "{store.owner.notnull}")
    private Seller owner;

    @Size(min = 1, max = 100)
    @NotNull(message = "{store.name.notnull}")
    private String name;

    private BigDecimal ratingAverage;

    @OneToMany(mappedBy = "store")
    private List<Rating> ratingList;

    @OneToMany(mappedBy = "id.store")
    private Set<StoreItem> storeItems;

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private List<Demand> demands;

    public Store(Seller owner, String name, BigDecimal ratingAverage) {
        this.owner = owner;
        this.name = name;
        this.ratingAverage = ratingAverage;
    }

    public Store() {
    }

    public Seller getOwner() {
        return owner;
    }

    public void setOwner(Seller owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(BigDecimal rating) {
        this.ratingAverage = rating;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public Set<StoreItem> getStoreItems() {
        return storeItems;
    }

    public void setStoreItems(Set<StoreItem> storeItems) {
        this.storeItems = storeItems;
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }
}
