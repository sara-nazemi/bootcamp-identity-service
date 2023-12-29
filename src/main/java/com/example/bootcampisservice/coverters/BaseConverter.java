package com.example.bootcampisservice.coverters;


import org.mapstruct.Mapping;

import java.util.List;

public interface BaseConverter<E,D> {
    @Mapping(source = "insertDate", target = "insertDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    @Mapping(source = "version", target = "version")
    E convertEntity(D model);

    @Mapping(source = "insertDate", target = "insertDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    @Mapping(source = "version", target = "version")
    D convertDto(E model);

    List<E> converterEntities(List<D> models);

    List<D> converterDtoes(List<E> models);
}
