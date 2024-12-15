package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.MenuItemCategoryMapper;
import com.xformation.food_ordering_system.dto.MenuItemCategoryDto;
import com.xformation.food_ordering_system.model.MenuItemCategory;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.MenuItemCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuItemCategoryService extends BaseCrudService<MenuItemCategory, Integer, MenuItemCategoryDto> {
    private final MenuItemCategoryRepository menuItemCategoryRepository;
    private final MenuItemCategoryMapper menuItemCategoryMapper;

    protected MenuItemCategoryService(PageableValidator pageableValidator,
                                      MenuItemCategoryRepository menuItemCategoryRepository,
                                      MenuItemCategoryMapper menuItemCategoryMapper) {
        super(pageableValidator);
        this.menuItemCategoryRepository = menuItemCategoryRepository;
        this.menuItemCategoryMapper = menuItemCategoryMapper;
    }

    public MenuItemCategory getReferenceByName(String name) {
        return menuItemCategoryRepository.getReferenceByName(name);
    }

    @Override
    protected BaseCrudRepository<MenuItemCategory, Integer> getBaseRepository() {
        return menuItemCategoryRepository;
    }

    @Override
    protected BaseMapper<MenuItemCategory, MenuItemCategoryDto> getBaseMapper() {
        return menuItemCategoryMapper;
    }
}
