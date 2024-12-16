package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.CuisineMapper;
import com.xformation.food_ordering_system.converter.CuisineMapperImpl;
import com.xformation.food_ordering_system.dto.CuisineDto;
import com.xformation.food_ordering_system.model.Cuisine;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.CuisineRepository;
import com.xformation.food_ordering_system.util.BuilderProvider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

public class CuisineServiceTest extends BaseCrudServiceTest<Cuisine, Integer, CuisineDto> {

    @Spy
    private CuisineMapper cuisineMapper = new CuisineMapperImpl();

    @Mock
    private CuisineRepository cuisineRepository;

    @InjectMocks
    private CuisineService cuisineService;


    @Override
    protected BaseCrudRepository<Cuisine, Integer> getRepository() {
        return cuisineRepository;
    }

    @Override
    protected BaseMapper<Cuisine, CuisineDto> getMapper() {
        return cuisineMapper;
    }

    @Override
    protected BaseCrudService<Cuisine, Integer, CuisineDto> getService() {
        return cuisineService;
    }

    @Override
    protected Cuisine createEntity() {
        return BuilderProvider.EntityBuilderProvider.getCuisineBuilder().build();
    }

    @Override
    protected CuisineDto createDTO() {
        return BuilderProvider.DtoBuilderProvider.getCuisineDtoBuilder().build();
    }

    @Override
    protected Integer mockId() {
        return BuilderProvider.mockId();
    }
}
