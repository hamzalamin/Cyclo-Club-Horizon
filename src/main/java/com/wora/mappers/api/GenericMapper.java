package com.wora.mappers.api;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenericMapper<Entity, Dto> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
    Entity updateEntity(Dto dto);
    Entity createEntity(Dto dto);
}
