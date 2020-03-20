package com.github.nicolasholanda.debt.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category extends BaseEntity<Integer> {

    @NotNull(message = "{category.name.notnull}")
    @Size(min = 3, max = 120, message = "{category.name.size}")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
