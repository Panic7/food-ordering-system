package com.xformation.food_ordering_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public abstract class BaseCrudServiceTest<ENTITY, ID, DTO> {

    @Mock
    private PageableValidator pageableValidator;

    protected abstract BaseCrudRepository<ENTITY, ID> getRepository();

    protected abstract BaseMapper<ENTITY, DTO> getMapper();

    protected abstract BaseCrudService<ENTITY, ID, DTO> getService();

    protected abstract ENTITY createEntity();

    protected abstract DTO createDTO();

    protected abstract ID mockId();

    protected void additionalStubbing() {
    }

    @Test
    void whenFindAllWithCommonParametersShouldReturnPageWithDTO() {
        ENTITY entity = createEntity();
        DTO dto = createDTO();
        Page<ENTITY> entityPage = new PageImpl<>(Collections.singletonList(entity));
        when(getRepository().findAll(any(Example.class), any(Pageable.class))).thenReturn(entityPage);
        doNothing().when(pageableValidator).validate(any(Pageable.class), anyInt());
        additionalStubbing();

        Page<DTO> result = getService().findAll(Example.of(dto), PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(dto, result.getContent().getFirst());
        verify(getMapper(), times(1)).toDto(any());
        verify(getMapper(), times(1)).toEntity(any());
    }

    @Test
    void whenFindByIdShouldReturnDtoWithId() {
        ENTITY entity = createEntity();
        DTO dto = createDTO();
        when(getRepository().findById(any())).thenReturn(Optional.of(entity));
        additionalStubbing();

        DTO result = getService().findById(mockId());

        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void testFindByIdNotFound() {
        var mockId = mockId();
        var service = getService();
        when(getRepository().findById(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.findById(mockId));
    }

    @Test
    void testFindOne() {
        ENTITY entity = createEntity();
        DTO dto = createDTO();
        when(getRepository().findOne(any(Example.class))).thenReturn(Optional.of(entity));
        additionalStubbing();

        DTO result = getService().findOne(Example.of(dto));

        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void testSaveAndFlush() {
        ENTITY entity = createEntity();
        DTO dto = createDTO();
        when(getRepository().save(any())).thenReturn(entity);
        doNothing().when(getRepository()).flush();
        additionalStubbing();

        DTO result = getService().saveAndFlush(dto);

        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void testSave() {
        ENTITY entity = createEntity();
        DTO dto = createDTO();
        when(getRepository().save(any())).thenReturn(entity);
        additionalStubbing();

        DTO result = getService().save(dto);

        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void testDelete() {
        ENTITY entity = createEntity();
        doNothing().when(getRepository()).delete(any());

        getService().delete(entity);

        verify(getRepository(), times(1)).delete(any());
    }

    @Test
    void testDeleteById() {
        doNothing().when(getRepository()).deleteById(any());

        getService().deleteById(mockId());

        verify(getRepository(), times(1)).deleteById(any());
    }

}