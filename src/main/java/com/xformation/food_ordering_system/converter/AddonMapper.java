package com.xformation.food_ordering_system.converter;

import com.xformation.food_ordering_system.dto.AddonDto;
import com.xformation.food_ordering_system.model.Addon;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddonMapper extends BaseMapper<Addon, AddonDto> {

    List<AddonDto> toDtoList(List<Addon> addons);

}
