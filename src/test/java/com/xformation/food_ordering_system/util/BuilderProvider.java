package com.xformation.food_ordering_system.util;

import com.xformation.food_ordering_system.dto.AddonDto;
import com.xformation.food_ordering_system.dto.CuisineDto;
import com.xformation.food_ordering_system.dto.MenuItemCategoryDto;
import com.xformation.food_ordering_system.dto.MenuItemDto;
import com.xformation.food_ordering_system.dto.OrderDto;
import com.xformation.food_ordering_system.dto.OrderItemDto;
import com.xformation.food_ordering_system.model.Addon;
import com.xformation.food_ordering_system.model.Cuisine;
import com.xformation.food_ordering_system.model.MenuItem;
import com.xformation.food_ordering_system.model.MenuItemCategory;
import com.xformation.food_ordering_system.model.Order;
import com.xformation.food_ordering_system.model.OrderItem;
import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@UtilityClass
public class BuilderProvider {
    private final Random RANDOM = new SecureRandom();

    public static Integer mockId() {
        return RANDOM.nextInt();
    }

    @UtilityClass
    public class EntityBuilderProvider {

        public Addon.AddonBuilder getAddonBuilder() {
            return Addon.builder().id(RANDOM.nextInt()).name("Lemon").price(1.0);
        }

        public Cuisine.CuisineBuilder getCuisineBuilder() {
            return Cuisine.builder().id(RANDOM.nextInt()).name("Regular");
        }

        public MenuItemCategory.MenuItemCategoryBuilder getMenuItemCategoryBuilder() {
            return MenuItemCategory.builder().id(RANDOM.nextInt()).name("Main Course");
        }

        public MenuItem.MenuItemBuilder getMenuItemBuilder() {
            return MenuItem.builder()
                    .id(RANDOM.nextInt())
                    .name("Pizza")
                    .price(10.99)
                    .category(getMenuItemCategoryBuilder().build())
                    .cuisine(getCuisineBuilder().build())
                    .addons(List.of(getAddonBuilder().build()));
        }

        public OrderItem.OrderItemBuilder getOrderItemBuilder() {
            return OrderItem.builder()
                    .id(RANDOM.nextInt())
                    .menuItem(getMenuItemBuilder().build())
                    .addons(List.of(getAddonBuilder().build()));
        }

        public Order.OrderBuilder getOrderBuilder() {
            return Order.builder()
                    .id(RANDOM.nextInt())
                    .orderItems(List.of(getOrderItemBuilder().build()));
        }

    }

    @UtilityClass
    public class DtoBuilderProvider {

        public AddonDto.AddonDtoBuilder getAddonDtoBuilder() {
            return AddonDto.builder().name("Lemon").price(1.0);
        }

        public CuisineDto.CuisineDtoBuilder getCuisineDtoBuilder() {
            return CuisineDto.builder().name("Regular");
        }

        public MenuItemCategoryDto.MenuItemCategoryDtoBuilder getMenuItemCategoryDtoBuilder() {
            return MenuItemCategoryDto.builder().name("Main Course");
        }

        public MenuItemDto.MenuItemDtoBuilder getMenuItemDtoBuilder() {
            return MenuItemDto.builder()
                    .name("Pizza")
                    .price(10.99)
                    .category(getMenuItemCategoryDtoBuilder().build())
                    .cuisine(getCuisineDtoBuilder().build())
                    .addons(List.of(getAddonDtoBuilder().build()));
        }

        public OrderItemDto.OrderItemDtoBuilder getOrderItemDtoBuilder() {
            var menuItemDto = getMenuItemDtoBuilder().build();
            return OrderItemDto.builder()
                    .menuItem(menuItemDto)
                    .addons(menuItemDto.addons());
        }

        public OrderDto.OrderDtoBuilder getOrderDtoBuilder() {
            return OrderDto.builder()
                    .publicId("hash")
                    .orderItems(List.of(getOrderItemDtoBuilder().build()));
        }
    }

}
