package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.dto.ExistentDemandDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ExistentDemandMapper {
    public ExistentDemandDTO toDTO(Demand demand) {
        return new ExistentDemandDTO(demand);
    }
}
