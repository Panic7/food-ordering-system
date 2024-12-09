package com.xformation.food_ordering_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "desserts")
public class Dessert extends MenuItem {
}
