package com.xformation.food_ordering_system.converter;

import com.xformation.food_ordering_system.dto.CuisineDto;
import com.xformation.food_ordering_system.model.Cuisine;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CuisineMapper extends BaseMapper<Cuisine, CuisineDto> {

    List<CuisineDto> toDtoList(List<Cuisine> cuisines);
}
