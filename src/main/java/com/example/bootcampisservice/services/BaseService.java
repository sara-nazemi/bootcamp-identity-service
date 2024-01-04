package com.example.bootcampisservice.services;

import java.util.List;

public interface BaseService <E,ID>{

    E save(E entity);

    E findById(ID id);

    void deleteById(ID id);

    List<E> findAll();
}
