package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.exceptions.AppException;

import java.util.List;

public interface CommonService<E, ID> {
    List<E> getAll();

    E getById(ID id) throws AppException;

    E save(E entity);

    List<E> save(List<E> dto);

    boolean existsById(ID id);

    void deleteById(ID id);

    void deleteByIdIn(List<ID> ids);
}
