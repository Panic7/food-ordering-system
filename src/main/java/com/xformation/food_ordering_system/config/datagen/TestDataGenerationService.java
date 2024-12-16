package com.xformation.food_ordering_system.config.datagen;

import com.xformation.food_ordering_system.model.Addon;
import com.xformation.food_ordering_system.model.Cuisine;
import com.xformation.food_ordering_system.model.MenuItem;
import com.xformation.food_ordering_system.model.MenuItemCategory;
import com.xformation.food_ordering_system.repository.AddonRepository;
import com.xformation.food_ordering_system.repository.CuisineRepository;
import com.xformation.food_ordering_system.repository.MenuItemCategoryRepository;
import com.xformation.food_ordering_system.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("test-data")
@Slf4j
@RequiredArgsConstructor
@Service
public class TestDataGenerationService {
    private final MenuItemCategoryRepository menuItemCategoryRepository;
    private final AddonRepository addonRepository;
    private final CuisineRepository cuisineRepository;
    private final MenuItemRepository menuItemRepository;

    @Transactional
    public void generateTestData() {
        var categories = List.of(
                MenuItemCategory.builder()
                        .name("Main Course")
                        .build(),
                MenuItemCategory.builder()
                        .name("Dessert")
                        .build(),
                MenuItemCategory.builder()
                        .name("Drink")
                        .build()
        );
        menuItemCategoryRepository.saveAll(categories);

        var cuisines = List.of(
                Cuisine.builder()
                        .name("Italian")
                        .build(),
                Cuisine.builder()
                        .name("Mexican")
                        .build(),
                Cuisine.builder()
                        .name("Polish")
                        .build()
        );
        cuisineRepository.saveAll(cuisines);

        var addons = List.of(
                Addon.builder()
                        .name("Lemon")
                        .price(0.0)
                        .build(),
                Addon.builder()
                        .name("Ice Cubes")
                        .price(0.0)
                        .build());
        addonRepository.saveAll(addons);

        var menuItems = List.of(
                MenuItem.builder()
                        .name("Pizza Margherita")
                        .price(9.99)
                        .category(categories.get(0))
                        .cuisine(cuisines.get(0))
                        .build(),
                MenuItem.builder()
                        .name("Tiramisu")
                        .price(4.99)
                        .category(categories.get(1))
                        .cuisine(cuisines.get(0))
                        .build(),
                MenuItem.builder()
                        .name("Taco")
                        .price(7.99)
                        .category(categories.get(0))
                        .cuisine(cuisines.get(1))
                        .build(),
                MenuItem.builder()
                        .name("Churros")
                        .price(3.99)
                        .category(categories.get(1))
                        .cuisine(cuisines.get(1))
                        .build(),
                MenuItem.builder()
                        .name("Pierogi")
                        .price(6.99)
                        .category(categories.get(0))
                        .cuisine(cuisines.get(2))
                        .build(),
                MenuItem.builder()
                        .name("Sernik")
                        .price(5.99)
                        .category(categories.get(1))
                        .cuisine(cuisines.get(2))
                        .build(),
                MenuItem.builder()
                        .name("Coffee")
                        .price(2.99)
                        .category(categories.get(2))
                        .cuisine(cuisines.get(2))
                        .addons(addons)
                        .build()
        );
        menuItemRepository.saveAll(menuItems);
    }
}