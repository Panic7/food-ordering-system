package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.AddonMapper;
import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.dto.AddonDto;
import com.xformation.food_ordering_system.model.Addon;
import com.xformation.food_ordering_system.repository.AddonRepository;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AddonService extends BaseCrudService<Addon, Integer, AddonDto> {
    private final AddonRepository addonRepository;
    private final AddonMapper addonMapper;

    protected AddonService(PageableValidator pageableValidator,
                           AddonRepository addonRepository,
                           AddonMapper addonMapper) {
        super(pageableValidator);
        this.addonRepository = addonRepository;
        this.addonMapper = addonMapper;
    }

    public Addon getReferenceByName(String name) {
        return addonRepository.getReferenceByName(name);
    }

    @Override
    protected BaseCrudRepository<Addon, Integer> getBaseRepository() {
        return addonRepository;
    }

    @Override
    protected BaseMapper<Addon, AddonDto> getBaseMapper() {
        return addonMapper;
    }

}