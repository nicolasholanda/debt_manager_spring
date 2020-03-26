package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO extends BaseDTO<Integer> {

    @NotNull(message = "{category.name.notnull}")
    @Size(min = 3, max = 120, message = "{category.name.size}")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        super(category.getId());
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CategoryDTO fromModel(Category category) {
        return new CategoryDTO(category);
    }

    public static Category toModel(CategoryDTO dto) {
        var category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}
