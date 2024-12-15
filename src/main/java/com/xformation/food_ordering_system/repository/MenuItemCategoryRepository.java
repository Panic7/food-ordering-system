package com.xformation.food_ordering_system.repository;

import com.xformation.food_ordering_system.model.MenuItemCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemCategoryRepository extends BaseCrudRepository<MenuItemCategory, Integer> {
    MenuItemCategory getReferenceByName(String name);
}