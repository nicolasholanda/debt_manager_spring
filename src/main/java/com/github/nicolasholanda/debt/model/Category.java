package com.github.nicolasholanda.debt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

import static java.util.Collections.emptyList;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "category")
public class Category extends BaseEntity<Integer> {

    @Column(unique = true)
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
