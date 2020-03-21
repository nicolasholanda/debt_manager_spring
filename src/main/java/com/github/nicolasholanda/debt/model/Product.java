package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.nicolasholanda.debt.model.enuns.Gender;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
@Table(name = "product")
public class Product extends BaseEntity<Integer> {

    @Size(min = 3, max = 600, message = "{product.name.size}")
    @NotNull(message = "{product.name.notnull}")
    private String name;

    @ManyToOne
    @NotNull(message = "{product.brand.notnull}")
    private Brand brand;

    @Min(0)
    @NotNull(message = "{product.price.notnull}")
    private BigDecimal price;

    @Size(min = 3, max = 1500, message = "{product.description.size}")
    @NotNull(message = "{product.description.notnull}")
    private String description;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Category> categories = emptyList();

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Product() {
    }

    public Product(String name, Brand brand, BigDecimal price, String description, List<Category> categories, Gender gender) {
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
        return gender;
    }
}
