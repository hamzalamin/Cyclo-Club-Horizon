package com.wora.mappers;

import com.wora.mappers.api.GenericMapper;
import com.wora.models.dtos.rider.CreateRiderDto;
import com.wora.models.dtos.rider.RiderDto;
import com.wora.models.dtos.rider.UpdateRiderDto;
import com.wora.models.entities.Rider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RiderMapper extends GenericMapper<Rider, RiderDto> {
//    Rider toEntity(RiderDto dto);
    Rider toEntity(CreateRiderDto dto);
    Rider toEntity(UpdateRiderDto dto);
    RiderDto toDto(Rider rider);

}
