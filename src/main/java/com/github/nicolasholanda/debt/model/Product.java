package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.nicolasholanda.debt.model.enuns.Gender;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

@Entity
@Table(name = "product")
public class Product extends BaseEntity<Integer> {

    @Size(min = 3, max = 600, message = "{product.name.size}")
    @NotNull(message = "{product.name.notnull}")
    private String name;

    @ManyToOne
    @NotNull(message = "{product.brand.notnull}")
    private Brand brand;

    @Min(value = 0, message = "{product.price.min}")
    @NotNull(message = "{product.price.notnull}")
    private BigDecimal price;

    @Size(min = 3, max = 1500, message = "{product.description.size}")
    @NotNull(message = "{product.description.notnull}")
    private String description;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = emptyList();

    @NotNull(message = "{product.gender.notnull}")
    private Integer gender;

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<DemandItem> demandItems = emptySet();

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<StoreItem> storeItems = emptySet();

    public Product() {
    }

    public Product(String name, Brand brand, BigDecimal price, String description, List<Category> categories, Integer gender) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.categories = categories;
        this.description = description;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Brand getBrand() {
        return brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Gender getGender() {
        return Gender.of(gender);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setGender(Integer gender) {
        Gender.of(gender);
        this.gender = gender;
    }

    public Set<DemandItem> getDemandItems() {
        return demandItems;
    }

    public void setDemandItems(Set<DemandItem> items) {
        this.demandItems = items;
    }

    @JsonIgnore
    public List<Demand> getDemands() {
        return demandItems.stream().map(DemandItem::getDemand).collect(Collectors.toList());
    }
}
