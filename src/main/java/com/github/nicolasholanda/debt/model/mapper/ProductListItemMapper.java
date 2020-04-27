package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.Product;
import com.github.nicolasholanda.debt.model.dto.ProductListItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductListItemMapper {
    @Mapping(target = "brand", expression = "java(product.getBrand().getName())")
    ProductListItemDTO toDTO(Product product);
}
