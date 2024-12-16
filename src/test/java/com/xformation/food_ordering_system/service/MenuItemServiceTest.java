package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.converter.MenuItemMapper;
import com.xformation.food_ordering_system.converter.MenuItemMapperImpl;
import com.xformation.food_ordering_system.dto.MenuItemDto;
import com.xformation.food_ordering_system.model.MenuItem;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import com.xformation.food_ordering_system.repository.MenuItemRepository;
import com.xformation.food_ordering_system.util.BuilderProvider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

public class MenuItemServiceTest extends BaseCrudServiceTest<MenuItem, Integer, MenuItemDto> {

    @Spy
    private MenuItemMapper menuItemMapper = new MenuItemMapperImpl();

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemCategoryService menuItemCategoryService;

    @Mock
    private CuisineService cuisineService;

    @Mock
    private AddonService addonService;

    @InjectMocks
    private MenuItemService menuItemService;

    @Override
    protected BaseCrudRepository<MenuItem, Integer> getRepository() {
        return menuItemRepository;
    }

    @Override
    protected BaseMapper<MenuItem, MenuItemDto> getMapper() {
        return menuItemMapper;
    }

    @Override
    protected BaseCrudService<MenuItem, Integer, MenuItemDto> getService() {
        return menuItemService;
    }

    @Override
    protected MenuItem createEntity() {
        return BuilderProvider.EntityBuilderProvider.getMenuItemBuilder().build();
    }

    @Override
    protected MenuItemDto createDTO() {
        return BuilderProvider.DtoBuilderProvider.getMenuItemDtoBuilder().build();
    }

    @Override
    protected Integer mockId() {
        return BuilderProvider.mockId();
    }
}
