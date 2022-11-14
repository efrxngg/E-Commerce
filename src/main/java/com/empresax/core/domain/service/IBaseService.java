package com.empresax.core.domain.service;

import java.util.List;

public interface IBaseService<T, ID> {

    T create(T entity);

    T findById(ID id);

    List<T> findAll();

    T updateById(T entity);

    void deleteById(ID id);

}