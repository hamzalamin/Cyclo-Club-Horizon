package com.wora.mappers.api;

import org.mapstruct.Mapper;

@Mapper
public interface GenericMapper<Entity, Dto> {
    Dto toDto(Dto dto);
    Entity toEntity(Entity entity);
}
