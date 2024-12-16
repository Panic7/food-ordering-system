package com.xformation.food_ordering_system;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class FoodOrderingSystemApplication implements CommandLineRunner {

    private final OrderCLI orderCLI;

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderingSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {
        orderCLI.start();
    }
}
