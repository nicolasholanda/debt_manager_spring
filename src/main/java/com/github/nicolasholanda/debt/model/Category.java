package com.github.nicolasholanda.debt.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static java.util.Collections.emptyList;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "category")
public class Category extends BaseEntity<Integer> {

    @NotNull(message = "{category.name.notnull}")
    @Size(min = 3, max = 120, message = "{category.name.size}")
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = LAZY)
    private List<Product> products = emptyList();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
