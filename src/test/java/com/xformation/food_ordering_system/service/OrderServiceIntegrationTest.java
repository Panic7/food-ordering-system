package com.xformation.food_ordering_system.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import com.xformation.food_ordering_system.dto.OrderDto;
import com.xformation.food_ordering_system.repository.OrderRepository;
import com.xformation.food_ordering_system.util.BuilderProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private AddonService addonService;

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private MenuItemCategoryService menuItemCategoryService;

    @Autowired
    private OrderRepository orderRepository;

    private static final OrderDto orderDto = BuilderProvider.DtoBuilderProvider.getOrderDtoBuilder().publicId(null)
            .build();

    @Test
    void testSaveOrder() {
        orderDto.orderItems().forEach(orderItemDto -> {
            orderItemDto.addons().forEach(addonService::save);
            cuisineService.save(orderItemDto.menuItem().cuisine());
            menuItemCategoryService.save(orderItemDto.menuItem().category());
            menuItemService.save(orderItemDto.menuItem());
        });
        assertThatNoException().isThrownBy(() -> orderService.saveAndFlush(orderDto));

        assertThat(orderService.findAll(Pageable.ofSize(10))).hasSize(1);
        assertThat(orderItemService.findAll(Pageable.ofSize(10))).hasSize(orderDto.orderItems().size());
    }
}