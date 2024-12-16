package com.xformation.food_ordering_system.converter;

public interface BaseMapper<Entity, DTO> {
    Entity toEntity(DTO dto);

    DTO toDto(Entity entity);
}