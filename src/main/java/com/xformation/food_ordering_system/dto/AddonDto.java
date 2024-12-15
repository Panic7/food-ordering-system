package com.xformation.food_ordering_system.dto;

import lombok.Builder;

@Builder
public record AddonDto(String name, Double price) {
}