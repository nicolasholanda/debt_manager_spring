package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.dto.DemandListItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DemandListItemMapper {
    public DemandListItemDTO toDTO(Demand demand) {
        return new DemandListItemDTO(demand);
    }
}
