package com.wora.mappers.api;

import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface GenericMapper<Entity, Dto> {
    Entity toEntity(Dto dto);
    Dto toDto(Entity entity);
}
