package com.xformation.food_ordering_system.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderDto(String publicId, List<OrderItemDto> orderItems) {
}
