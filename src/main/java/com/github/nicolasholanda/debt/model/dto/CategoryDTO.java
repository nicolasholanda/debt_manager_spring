package com.github.nicolasholanda.debt.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO extends BaseDTO<Integer> {

    @NotNull(message = "{category.name.notnull}")
    @Size(min = 3, max = 120, message = "{category.name.size}")
    private String name;

    public CategoryDTO(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
