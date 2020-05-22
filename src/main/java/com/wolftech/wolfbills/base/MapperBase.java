package com.wolftech.wolfbills.base;

import java.util.List;

public interface MapperBase<E, D> {

    D toDto(E entity);
    E toEntity(D dto);

    List<D> toDto(List<E> entities);
    List<E> toEntity(List<D> dtos);

}
