package com.wora.mappers;

import com.wora.mappers.api.GenericMapper;
import com.wora.models.dtos.Team.CreateTeamDto;
import com.wora.models.dtos.Team.TeamDto;
import com.wora.models.dtos.Team.UpdateTeamDto;
import com.wora.models.entities.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper extends GenericMapper<Team, TeamDto> {
//    Team toEntity(TeamDto dto);
    Team toEntity(CreateTeamDto dto);
    Team toEntity(UpdateTeamDto dto);
    TeamDto toDto(Team entity);
}
