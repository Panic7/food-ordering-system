package com.xformation.food_ordering_system.repository;

import com.xformation.food_ordering_system.model.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends BaseCrudRepository<OrderItem, Integer> {
}