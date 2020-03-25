package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotNull(message = "{category.name.notnull}")
    @Size(min = 3, max = 120, message = "{category.name.size}")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
