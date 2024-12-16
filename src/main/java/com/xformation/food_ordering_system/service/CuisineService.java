package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.CuisineMapper;
import com.xformation.food_ordering_system.dto.CuisineDto;
import com.xformation.food_ordering_system.model.Cuisine;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.CuisineRepository;
import org.springframework.stereotype.Service;

@Service
public class CuisineService extends BaseCrudService<Cuisine, Integer, CuisineDto> {
    private final CuisineRepository cuisineRepository;
    private final CuisineMapper cuisineMapper;

    public CuisineService(PageableValidator pageableValidator,
                          CuisineRepository cuisineRepository,
                          CuisineMapper cuisineMapper) {
        super(pageableValidator);
        this.cuisineRepository = cuisineRepository;
        this.cuisineMapper = cuisineMapper;
    }

    public Cuisine getReferenceByName(String name) {
        return cuisineRepository.getReferenceByName(name);
    }

    @Override
    protected BaseCrudRepository<Cuisine, Integer> getBaseRepository() {
        return cuisineRepository;
    }

    @Override
    protected BaseMapper<Cuisine, CuisineDto> getBaseMapper() {
        return cuisineMapper;
    }
}