package com.evolvestech.library_management.mappers;

public interface BaseMapper<E, D> {
    D mapEntityToDto(E entity);

    E mapDtoToEntity(D dto);
}