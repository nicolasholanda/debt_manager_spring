package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.Category;
import com.github.nicolasholanda.debt.model.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toModel(CategoryDTO dto);
}
