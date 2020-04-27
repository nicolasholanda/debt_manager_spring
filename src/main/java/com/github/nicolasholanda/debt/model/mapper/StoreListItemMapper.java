package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.Store;
import com.github.nicolasholanda.debt.model.dto.StoreListItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StoreListItemMapper {
    public StoreListItemDTO toDTO(Store store) {
        return new StoreListItemDTO(store);
    }
}
