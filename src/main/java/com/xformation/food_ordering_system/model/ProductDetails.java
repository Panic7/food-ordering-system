package com.xformation.food_ordering_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;

@Data
@NoArgsConstructor
@Embeddable
@SuperBuilder
@Table(name = "product_details")
public class ProductDetails {

    @NaturalId
    private String name;

    @Column(nullable = false)
    private Double price;

}
