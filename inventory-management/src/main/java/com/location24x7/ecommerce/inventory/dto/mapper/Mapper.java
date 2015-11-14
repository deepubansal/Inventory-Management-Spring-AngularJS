package com.location24x7.ecommerce.inventory.dto.mapper;

import java.util.List;
import java.util.Set;

public interface Mapper<E, D> {

    public abstract E createEntity(D dto);

    public abstract D createDto(E entity);

    public abstract List<D> createDtos(Iterable<E> entities);

    public abstract Set<E> createEntities(Iterable<D> dtos);

}