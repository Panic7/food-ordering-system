package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.MenuItemCategoryMapper;
import com.xformation.food_ordering_system.converter.MenuItemCategoryMapperImpl;
import com.xformation.food_ordering_system.dto.MenuItemCategoryDto;
import com.xformation.food_ordering_system.model.MenuItemCategory;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.MenuItemCategoryRepository;
import com.xformation.food_ordering_system.util.BuilderProvider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

public class MenuItemCategoryServiceTest extends BaseCrudServiceTest<MenuItemCategory, Integer, MenuItemCategoryDto> {

    @Spy
    private MenuItemCategoryMapper menuItemCategoryMapper = new MenuItemCategoryMapperImpl();

    @Mock
    private MenuItemCategoryRepository menuItemCategoryRepository;

    @InjectMocks
    private MenuItemCategoryService menuItemCategoryService;

    @Override
    protected BaseCrudRepository<MenuItemCategory, Integer> getRepository() {
        return menuItemCategoryRepository;
    }

    @Override
    protected BaseMapper<MenuItemCategory, MenuItemCategoryDto> getMapper() {
        return menuItemCategoryMapper;
    }

    @Override
    protected BaseCrudService<MenuItemCategory, Integer, MenuItemCategoryDto> getService() {
        return menuItemCategoryService;
    }

    @Override
    protected MenuItemCategory createEntity() {
        return BuilderProvider.EntityBuilderProvider.getMenuItemCategoryBuilder().build();
    }

    @Override
    protected MenuItemCategoryDto createDTO() {
        return BuilderProvider.DtoBuilderProvider.getMenuItemCategoryDtoBuilder().build();
    }

    @Override
    protected Integer mockId() {
        return BuilderProvider.mockId();
    }
}
