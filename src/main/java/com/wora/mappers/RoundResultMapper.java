package com.wora.mappers;

import com.wora.mappers.api.GenericMapper;
import com.wora.models.dtos.roundResult.CreateRoundResultDto;
import com.wora.models.dtos.roundResult.RoundResultDto;
import com.wora.models.dtos.roundResult.UpdateRoundResultDto;
import com.wora.models.entities.RoundResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoundResultMapper extends GenericMapper<RoundResult, RoundResultDto> {
//     RoundResult toEntity(RoundResultDto dto);
     RoundResult toEntity(CreateRoundResultDto dto);
     RoundResult toEntity(UpdateRoundResultDto dto);
     RoundResultDto toDto(RoundResult roundResult);
}
