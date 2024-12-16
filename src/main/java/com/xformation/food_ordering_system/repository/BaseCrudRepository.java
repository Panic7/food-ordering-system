package com.xformation.food_ordering_system.repository;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseCrudRepository<T, ID> extends org.springframework.data.repository.Repository<T, ID> {

    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends T> Page<S> findAll(Pageable pageable);

    <S extends T> Optional<S> findOne(Example<S> example);

    <S extends T> Optional<S> findById(ID id);

    T getReferenceById(ID id);

    <S extends T> void saveAll(Iterable<S> entities);

    <S extends T> S save(S entity);

    void flush();

    void delete(T entity);

    void deleteById(ID id);

}
