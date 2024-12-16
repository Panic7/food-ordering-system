package com.xformation.food_ordering_system.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderItemDto(MenuItemDto menuItem, List<AddonDto> addons) {
    public OrderItemDto {
        if (addons == null) {
            addons = List.of();
        }
    }
}