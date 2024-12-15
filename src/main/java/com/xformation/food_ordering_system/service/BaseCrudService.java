package com.xformation.food_ordering_system.service;

import com.xformation.food_ordering_system.converter.BaseMapper;
import com.xformation.food_ordering_system.repository.BaseCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public abstract class BaseCrudService<ENTITY, ID, DTO> {
    private final PageableValidator pageableValidator;

    protected BaseCrudService(PageableValidator pageableValidator) {
        this.pageableValidator = pageableValidator;
    }

    protected abstract BaseCrudRepository<ENTITY, ID> getBaseRepository();

    protected abstract BaseMapper<ENTITY, DTO> getBaseMapper();

    @Transactional(readOnly = true)
    public Page<DTO> findAll(Example<DTO> dtoExample, Pageable pageable) {
        pageableValidator.validate(pageable, getMaxPageSize());

        var entityProbe = toEntity(dtoExample.getProbe());
        var entityExample = Example.of(entityProbe, dtoExample.getMatcher());
        log.info("Finding entities by example: {}", entityExample);
        log.debug("Pageable: {}", pageable);

        return getBaseRepository().findAll(entityExample, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public Page<DTO> findAll(Pageable pageable) {
        pageableValidator.validate(pageable, getMaxPageSize());
        log.debug("Finding all entities, pageable: {}", pageable);

        return getBaseRepository().findAll(pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public DTO findOne(Example<DTO> dtoExample) {
        var entityProbe = toEntity(dtoExample.getProbe());
        var entityExample = Example.of(entityProbe, dtoExample.getMatcher());
        log.info("Finding entity by example: {}", entityExample);

        return getBaseRepository().findOne(entityExample).map(this::toDto).orElseThrow();
    }

    @Transactional(readOnly = true)
    public DTO findById(ID id) {
        log.debug("Finding entity by id: {}", id);
        return getBaseRepository().findById(id).map(this::toDto).orElseThrow();
    }

    @Transactional(readOnly = true)
    public ENTITY getReferenceById(ID id) {
        log.debug("Getting reference by id: {}", id);
        return getBaseRepository().getReferenceById(id);
    }

    @Transactional
    public DTO save(DTO dto) {
        log.debug("Saving dto: {}", dto);
        var entityToSave = toEntity(dto);

        return toDto(getBaseRepository().save(entityToSave));
    }

    public void delete(ENTITY entity) {
        log.info("Deleting entity: {}", entity);
        getBaseRepository().delete(entity);
    }

    public void deleteById(ID id) {
        log.info("Deleting entity: {} with id: {}", getEntityTypeClass(), id);
        getBaseRepository().deleteById(id);
    }

    @Transactional
    public DTO saveAndFlush(DTO dto) {
        log.debug("Saving and flushing dto: {}", dto);
        var savedEntityDto = save(dto);
        flush();
        return savedEntityDto;
    }

    protected void flush() {
        log.debug("Manual flushing repository");
        getBaseRepository().flush();
    }

    protected ENTITY toEntity(DTO dto) {
        var entity = getBaseMapper().toEntity(dto);
        log.debug("Converting dto to entity, entity: {}", entity);
        return entity;
    }

    protected DTO toDto(ENTITY entity) {
        var dto = getBaseMapper().toDto(entity);
        log.debug("Converting entity to dto, dto: {}", dto);
        return dto;
    }

    /**
     * Returns the maximum page size that can be requested.
     * Refer to the {@link PageableValidator} for more information.
     *
     * @return the maximum page size, default is 20
     */
    protected int getMaxPageSize() {
        return 20;
    }

    private Class<ENTITY> getEntityTypeClass() {
        @SuppressWarnings("unchecked")
        Class<ENTITY>[] classes = (Class<ENTITY>[]) GenericTypeResolver.resolveTypeArguments(getClass(),
                BaseCrudService.class);
        return classes[0];
    }

}