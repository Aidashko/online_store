package com.example.online_store_1.mapper;

import java.util.List;

public interface BaseMapper <E,D>{
    D toDto(E e);
    E toEntity(D d);
    List<E> toEntityList(List<D> ds);
    List<D> toDtoList(List<E> es);
}
