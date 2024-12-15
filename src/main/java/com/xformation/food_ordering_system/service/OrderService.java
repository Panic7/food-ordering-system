package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.OrderMapper;
import com.xformation.food_ordering_system.dto.AddonDto;
import com.xformation.food_ordering_system.dto.OrderDto;
import com.xformation.food_ordering_system.dto.OrderItemDto;
import com.xformation.food_ordering_system.model.Addon;
import com.xformation.food_ordering_system.model.Order;
import com.xformation.food_ordering_system.model.OrderItem;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService extends BaseCrudService<Order, Integer, OrderDto> {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final MenuItemService menuItemService;
    private final AddonService addonService;

    protected OrderService(PageableValidator pageableValidator,
                           OrderRepository orderRepository,
                           OrderMapper orderMapper,
                           MenuItemService menuItemService,
                           AddonService addonService) {
        super(pageableValidator);
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.menuItemService = menuItemService;
        this.addonService = addonService;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        var order = orderMapper.toEntity(orderDto);
        var managedOrderItems = getOrderItemsWithManagedAssociations(orderDto);
        order.setOrderItems(managedOrderItems);

        var managedOrder = orderRepository.save(order);

        return orderMapper.toDto(managedOrder);
    }

    private List<OrderItem> getOrderItemsWithManagedAssociations(OrderDto orderDto) {
        return orderDto.orderItems().stream()
                .map(this::getOrderItemWithManagedAssociations)
                .toList();
    }

    private OrderItem getOrderItemWithManagedAssociations(OrderItemDto orderItemDto) {
        var managedOrderItem = new OrderItem();

        var menuItemName = orderItemDto.menuItem().name();
        var menuItemReference = menuItemService.getReferenceByName(menuItemName);

        var managedAddons = getManagedAddons(orderItemDto.addons());

        managedOrderItem.setMenuItem(menuItemReference);
        managedOrderItem.setAddons(managedAddons);

        return managedOrderItem;
    }

    private List<Addon> getManagedAddons(List<AddonDto> addonDtos) {
        return addonDtos.stream()
                .map(addon -> addonService.getReferenceByName(addon.name()))
                .toList();
    }

    @Override
    protected BaseCrudRepository<Order, Integer> getBaseRepository() {
        return orderRepository;
    }

    @Override
    protected BaseMapper<Order, OrderDto> getBaseMapper() {
        return orderMapper;
    }
}
