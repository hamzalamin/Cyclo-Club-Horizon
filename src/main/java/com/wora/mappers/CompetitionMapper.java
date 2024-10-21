package com.wora.mappers;

import com.wora.mappers.api.GenericMapper;
import com.wora.models.dtos.competition.CompetitionDto;
import com.wora.models.dtos.competition.CreateCompetitionDto;
import com.wora.models.dtos.competition.UpdateCompetitionDto;
import com.wora.models.entities.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper extends GenericMapper<CompetitionDto, Competition> {
    Competition toEntity(CreateCompetitionDto dto);
    Competition updateEntity(UpdateCompetitionDto dto);
    CompetitionDto toDto(Competition entity);
}
