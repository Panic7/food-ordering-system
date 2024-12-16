package com.xformation.food_ordering_system.converter;

import com.xformation.food_ordering_system.dto.OrderItemDto;
import com.xformation.food_ordering_system.model.OrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemDto> {

    List<OrderItemDto> toDtoList(List<OrderItem> orderItems);
}