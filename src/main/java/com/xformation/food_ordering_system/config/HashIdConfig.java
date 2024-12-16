package com.xformation.food_ordering_system.config;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashIdConfig {

    @Bean
    public Hashids hashids(@Value("${hashids.salt}") String salt) {
        return new Hashids(salt);
    }
}
