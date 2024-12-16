package com.xformation.food_ordering_system.repository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.xformation.food_ordering_system.OrderCLI;
import com.xformation.food_ordering_system.model.Cuisine;
import com.xformation.food_ordering_system.model.MenuItem;
import com.xformation.food_ordering_system.model.MenuItemCategory;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@DataJpaTest
class MenuItemRepositoryTest {

    @MockitoBean
    private OrderCLI orderCLI;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void whenNameAndPriceAndCategoryAndCuisineAreNull_throwConstraintViolationException() {
        var menuItem = MenuItem.builder().build();
        assertThatThrownBy(() -> testEntityManager.persistAndFlush(menuItem))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContainingAll("name", "price", "category", "cuisine");
    }

    @Test
    void whenPriceIsNegative_thenConstraintViolationException() {
        var menuItemCategory = testEntityManager.persist(MenuItemCategory.builder().name("Drink").build());
        var cuisine = testEntityManager.persist(Cuisine.builder().name("Regular").build());
        var menuItem = MenuItem.builder()
                .name("Coke")
                .price(-1.0)
                .category(menuItemCategory)
                .cuisine(cuisine)
                .build();

        assertThatThrownBy(() -> testEntityManager.persistAndFlush(menuItem))
                .isInstanceOf(ConstraintViolationException.class).hasMessageContaining("price");
    }

}