package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.MenuItemMapper;
import com.xformation.food_ordering_system.dto.AddonDto;
import com.xformation.food_ordering_system.dto.MenuItemDto;
import com.xformation.food_ordering_system.model.Addon;
import com.xformation.food_ordering_system.model.MenuItem;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService extends BaseCrudService<MenuItem, Integer, MenuItemDto> {
    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;
    private final MenuItemCategoryService menuItemCategoryService;
    private final CuisineService cuisineService;
    private final AddonService addonService;

    protected MenuItemService(PageableValidator pageableValidator,
                              MenuItemRepository menuItemRepository,
                              MenuItemMapper menuItemMapper,
                              MenuItemCategoryService menuItemCategoryService,
                              CuisineService cuisineService,
                              AddonService addonService) {
        super(pageableValidator);
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
        this.menuItemCategoryService = menuItemCategoryService;
        this.cuisineService = cuisineService;
        this.addonService = addonService;
    }

    public MenuItem getReferenceByName(String name) {
        return menuItemRepository.getReferenceByName(name);
    }

    private List<Addon> getManagedAddons(List<AddonDto> addonDtos) {
        return addonDtos.stream()
                .map(addon -> addonService.getReferenceByName(addon.name()))
                .toList();
    }

    @Override
    public MenuItemDto save(MenuItemDto menuItemDto) {
        MenuItem menuItem = menuItemMapper.toEntity(menuItemDto);
        menuItem.setCategory(menuItemCategoryService.getReferenceByName(menuItemDto.category().name()));
        menuItem.setCuisine(cuisineService.getReferenceByName(menuItemDto.cuisine().name()));
        menuItem.setAddons(getManagedAddons(menuItemDto.addons()));
        return menuItemMapper.toDto(menuItemRepository.save(menuItem));
    }

    @Override
    protected BaseCrudRepository<MenuItem, Integer> getBaseRepository() {
        return menuItemRepository;
    }

    @Override
    protected BaseMapper<MenuItem, MenuItemDto> getBaseMapper() {
        return menuItemMapper;
    }
}