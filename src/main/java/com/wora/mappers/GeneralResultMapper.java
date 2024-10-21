package com.wora.mappers;

import com.wora.mappers.api.GenericMapper;
import com.wora.models.dtos.generalResult.CreateGeneralResultDto;
import com.wora.models.dtos.generalResult.GeneralResultDto;
import com.wora.models.dtos.generalResult.UpdateGeneralResultDto;
import com.wora.models.entities.GeneralResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralResultMapper extends GenericMapper<GeneralResult, GeneralResultDto> {
    GeneralResult toEntity(GeneralResultDto dto);
    GeneralResult createEntity(CreateGeneralResultDto dto);
    GeneralResult updateEntity(UpdateGeneralResultDto dto);
    GeneralResultDto toDto(GeneralResult generalResult);

}

