package com.xformation.food_ordering_system.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.OrderItemMapperImpl;
import com.xformation.food_ordering_system.converter.OrderMapper;
import com.xformation.food_ordering_system.converter.OrderMapperImpl;
import com.xformation.food_ordering_system.dto.OrderDto;
import com.xformation.food_ordering_system.model.Order;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.OrderRepository;
import com.xformation.food_ordering_system.util.BuilderProvider;
import org.hashids.Hashids;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

public class OrderServiceTest extends BaseCrudServiceTest<Order, Integer, OrderDto> {

    private Hashids hashids = mock(Hashids.class);

    @Spy
    private OrderMapper orderMapper = new OrderMapperImpl(new OrderItemMapperImpl()).setHashids(hashids);

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuItemService menuItemService;

    @Mock
    private AddonService addonService;

    @InjectMocks
    private OrderService orderService;

    @Override
    protected void additionalStubbing() {
        when(hashids.encode(any())).thenReturn("hash");
        when(hashids.decode("hash")).thenReturn(new long[]{1});
        lenient().when(menuItemService.getReferenceByName(any())).thenReturn(
                BuilderProvider.EntityBuilderProvider.getMenuItemBuilder().build());
        lenient().when(addonService.getReferenceByName(any())).thenReturn(
                BuilderProvider.EntityBuilderProvider.getAddonBuilder().build());
    }

    @Override
    protected BaseCrudRepository<Order, Integer> getRepository() {
        return orderRepository;
    }

    @Override
    protected BaseMapper<Order, OrderDto> getMapper() {
        return orderMapper;
    }

    @Override
    protected BaseCrudService<Order, Integer, OrderDto> getService() {
        return orderService;
    }

    @Override
    protected Order createEntity() {
        return BuilderProvider.EntityBuilderProvider.getOrderBuilder().build();
    }

    @Override
    protected OrderDto createDTO() {
        return BuilderProvider.DtoBuilderProvider.getOrderDtoBuilder().build();
    }

    @Override
    protected Integer mockId() {
        return BuilderProvider.mockId();
    }
}
