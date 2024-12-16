package com.xformation.food_ordering_system.repository;

import com.xformation.food_ordering_system.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseCrudRepository<Order, Integer> {
}
