package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static java.util.Collections.emptyList;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "brand")
public class Brand extends BaseEntity<Integer> {

    @Column(unique = true)
    @Size(min = 1, max = 120, message = "{brand.name.size}")
    @NotNull(message = "{brand.name.notnull}")
    private String name;

    @Size(max = 130, message = "{brand.description.size}")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", fetch = LAZY)
    private List<Product> products = emptyList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }
}
