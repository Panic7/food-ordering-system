package com.xformation.food_ordering_system.repository;

import com.xformation.food_ordering_system.model.Addon;
import org.springframework.stereotype.Repository;

@Repository
public interface AddonRepository extends BaseCrudRepository<Addon, Integer> {
    Addon getReferenceByName(String name);
}