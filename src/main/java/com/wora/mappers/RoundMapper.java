package com.wora.mappers;

import com.wora.mappers.api.GenericMapper;
import com.wora.models.dtos.round.CreateRoundDto;
import com.wora.models.dtos.round.RoundDto;
import com.wora.models.dtos.round.UpdateRoundDto;
import com.wora.models.entities.Round;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoundMapper extends GenericMapper<Round, RoundDto> {
//    Round toEntity(RoundDto dto);
    Round toEntity(CreateRoundDto dto);
    Round toEntity(UpdateRoundDto dto);
    RoundDto toDto(Round round);
}
