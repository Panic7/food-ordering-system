package com.xformation.food_ordering_system.repository;

import com.xformation.food_ordering_system.model.MenuItem;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends BaseCrudRepository<MenuItem, Integer> {
    MenuItem getReferenceByName(String name);
}