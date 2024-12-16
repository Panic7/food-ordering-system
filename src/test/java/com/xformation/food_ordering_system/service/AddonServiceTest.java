package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.AddonMapper;
import com.xformation.food_ordering_system.converter.AddonMapperImpl;
import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.dto.AddonDto;
import com.xformation.food_ordering_system.model.Addon;
import com.xformation.food_ordering_system.repository.AddonRepository;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.util.BuilderProvider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;


class AddonServiceTest extends BaseCrudServiceTest<Addon, Integer, AddonDto> {

    @Spy
    private AddonMapper addonMapper = new AddonMapperImpl();

    @Mock
    private AddonRepository addonRepository;

    @InjectMocks
    private AddonService addonService;

    @Override
    protected BaseCrudRepository<Addon, Integer> getRepository() {
        return addonRepository;
    }

    @Override
    protected BaseMapper<Addon, AddonDto> getMapper() {
        return addonMapper;
    }

    @Override
    protected BaseCrudService<Addon, Integer, AddonDto> getService() {
        return addonService;
    }

    @Override
    protected Addon createEntity() {
        return BuilderProvider.EntityBuilderProvider.getAddonBuilder().build();
    }

    @Override
    protected AddonDto createDTO() {
        return BuilderProvider.DtoBuilderProvider.getAddonDtoBuilder().build();
    }

    @Override
    protected Integer mockId() {
        return BuilderProvider.mockId();
    }

}
