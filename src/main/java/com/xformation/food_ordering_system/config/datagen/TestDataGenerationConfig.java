package com.xformation.food_ordering_system.config.datagen;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Profile("test-data")
@Configuration
@DependsOn(value = "testDataGenerationService")
public class TestDataGenerationConfig {

    TestDataGenerationConfig(TestDataGenerationService testDataGenerationService) {
        testDataGenerationService.generateTestData();
    }

}