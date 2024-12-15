package com.xformation.food_ordering_system.converter;

import com.xformation.food_ordering_system.dto.MenuItemDto;
import com.xformation.food_ordering_system.model.MenuItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MenuItemMapper extends BaseMapper<MenuItem, MenuItemDto> {

    List<MenuItemDto> toDtoList(List<MenuItem> menuItems);
}