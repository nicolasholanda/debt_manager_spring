package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "brand")
public class Brand extends BaseEntity<Integer> {

    @JsonProperty
    @Column(unique = true)
    @Size(min = 1, max = 120, message = "{brand.name.size}")
    @NotNull(message = "{brand.name.notnull}")
    private String name;

    @JsonProperty
    @Size(max = 130, message = "{brand.description.size}")
    private String description;

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
}
