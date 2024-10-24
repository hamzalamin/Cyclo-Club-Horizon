package com.wora.services;

import com.wora.models.dtos.Team.CreateTeamDto;
import com.wora.models.dtos.Team.TeamDto;
import com.wora.models.dtos.Team.UpdateTeamDto;
import java.util.List;

public interface ITeamService {
    TeamDto create(CreateTeamDto teamDto);
    TeamDto getById(Long id);
    List<TeamDto> getAll();
    TeamDto update(Long id, UpdateTeamDto teamDto);
    void delete(Long id);
}
