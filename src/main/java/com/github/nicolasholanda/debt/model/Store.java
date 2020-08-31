package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.nicolasholanda.debt.utils.AddressUtils;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "store")
public class Store extends BaseEntity<Integer> {

    @JsonIgnore
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "owner_id")
    private Seller owner;

    private String name;

    private BigDecimal ratingAverage;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "store")
    private List<Rating> ratingList;

    @OneToMany(mappedBy = "id.store")
    private Set<StoreItem> storeItems;

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private List<Demand> demands;

    @ManyToMany
    @JoinTable(
            name = "store_brand",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    private List<Brand> brands;

    public Store(Seller owner, String name, BigDecimal ratingAverage, Address address) {
        this.owner = owner;
        this.name = name;
        this.ratingAverage = ratingAverage;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public BigDecimal getDistance(Address address) {
        return AddressUtils.distanceInKm(this.address, address);
    }
}
