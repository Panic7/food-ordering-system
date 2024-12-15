package com.xformation.food_ordering_system.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MenuItemDto(String name, Double price, MenuItemCategoryDto category, CuisineDto cuisine,
                          List<AddonDto> addons) {
    public MenuItemDto {
        if (addons == null) {
            addons = List.of();
        }
    }
}
