package com.xformation.food_ordering_system.repository;

import com.xformation.food_ordering_system.model.Cuisine;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends BaseCrudRepository<Cuisine, Integer> {
    Cuisine getReferenceByName(String name);
}