package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.OrderItemMapper;
import com.xformation.food_ordering_system.converter.OrderItemMapperImpl;
import com.xformation.food_ordering_system.dto.OrderItemDto;
import com.xformation.food_ordering_system.model.OrderItem;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.OrderItemRepository;
import com.xformation.food_ordering_system.util.BuilderProvider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

public class OrderItemServiceTest extends BaseCrudServiceTest<OrderItem, Integer, OrderItemDto> {

    @Spy
    private OrderItemMapper orderItemMapper = new OrderItemMapperImpl();

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemService orderItemService;

    @Override
    protected BaseCrudRepository<OrderItem, Integer> getRepository() {
        return orderItemRepository;
    }

    @Override
    protected BaseMapper<OrderItem, OrderItemDto> getMapper() {
        return orderItemMapper;
    }

    @Override
    protected BaseCrudService<OrderItem, Integer, OrderItemDto> getService() {
        return orderItemService;
    }

    @Override
    protected OrderItem createEntity() {
        return BuilderProvider.EntityBuilderProvider.getOrderItemBuilder().build();
    }

    @Override
    protected OrderItemDto createDTO() {
        return BuilderProvider.DtoBuilderProvider.getOrderItemDtoBuilder().build();
    }

    @Override
    protected Integer mockId() {
        return BuilderProvider.mockId();
    }
}
