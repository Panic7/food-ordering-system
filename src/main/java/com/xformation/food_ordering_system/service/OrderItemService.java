package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.OrderItemMapper;
import com.xformation.food_ordering_system.dto.OrderItemDto;
import com.xformation.food_ordering_system.model.OrderItem;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends BaseCrudService<OrderItem, Integer, OrderItemDto> {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    protected OrderItemService(PageableValidator pageableValidator,
                               OrderItemRepository orderItemRepository,
                               OrderItemMapper orderItemMapper) {
        super(pageableValidator);
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    protected BaseCrudRepository<OrderItem, Integer> getBaseRepository() {
        return orderItemRepository;
    }

    @Override
    protected BaseMapper<OrderItem, OrderItemDto> getBaseMapper() {
        return orderItemMapper;
    }
}
