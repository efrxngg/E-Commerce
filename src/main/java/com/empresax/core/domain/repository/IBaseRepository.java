package com.empresax.core.domain.repository;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    T update(T entity, ID id);

    List<T> findAll();

    void delete(ID id);

}
