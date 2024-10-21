package com.wora.mappers;

import com.wora.mappers.api.GenericMapper;
import com.wora.models.dtos.round.CreateRoundDto;
import com.wora.models.dtos.round.RoundDto;
import com.wora.models.dtos.round.UpdateRoundDto;
import com.wora.models.entities.Round;

public interface RoundMapper extends GenericMapper<Round, RoundDto> {
    Round toEntity(RoundDto dto);
    Round createEntity(CreateRoundDto dto);
    Round updateEntity(UpdateRoundDto dto);
    RoundDto toDto(Round round);
}
