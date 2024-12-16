package com.xformation.food_ordering_system.converter;

import com.xformation.food_ordering_system.dto.MenuItemCategoryDto;
import com.xformation.food_ordering_system.model.MenuItemCategory;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MenuItemCategoryMapper extends BaseMapper<MenuItemCategory, MenuItemCategoryDto> {

    List<MenuItemCategoryDto> toDtoList(List<MenuItemCategory> menuItemCategories);
}