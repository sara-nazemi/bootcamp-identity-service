package com.example.bootcampisservice.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseServiceImpl<E,ID> implements BaseService<E,ID>{

    protected abstract JpaRepository<E, ID> getRepository();

    @Override
    public E save(E entity) {
        return getRepository().save(entity);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }
    public E findById(ID d){
        return (E) getRepository().findById(d);
    }

    @Override
    public List<E> findAll() {
        return getRepository().findAll();
    }
}
