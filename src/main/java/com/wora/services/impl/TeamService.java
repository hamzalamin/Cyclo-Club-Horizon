package com.wora.services.impl;

import com.wora.mappers.TeamMapper;
import com.wora.models.dtos.Team.CreateTeamDto;
import com.wora.models.dtos.Team.TeamDto;
import com.wora.models.dtos.Team.UpdateTeamDto;
import com.wora.models.entities.Team;
import com.wora.repositories.TeamRepository;
import com.wora.services.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMapper teamMapper;


    @Override
    public TeamDto create(CreateTeamDto teamDto) {
        Team team = teamMapper.createEntity(teamDto);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toDto(savedTeam);
    }

    @Override
    public TeamDto getById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        return teamMapper.toDto(team);
    }

    @Override
    public List<TeamDto> getAll() {
        List<TeamDto> result = teamRepository.findAll().stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public TeamDto update(Long id, UpdateTeamDto teamDto) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        teamMapper.updateEntity(teamDto);
        Team updatedTeam = teamRepository.save(team);
        return teamMapper.toDto(updatedTeam);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

}
