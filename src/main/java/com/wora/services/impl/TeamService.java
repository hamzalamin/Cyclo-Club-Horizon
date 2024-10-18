package com.wora.services.impl;

import com.wora.models.dtos.requests.TeamDtoReq;
import com.wora.models.entities.Team;
import com.wora.repositories.TeamRepository;
import com.wora.services.ITeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TeamDtoReq create(TeamDtoReq teamDto) {
        Team team = modelMapper.map(teamDto, Team.class);
        Team savedTeam = teamRepository.save(team);
        return modelMapper.map(savedTeam, TeamDtoReq.class);
    }

    @Override
    public TeamDtoReq getById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        return toDtoRes(team);
    }

    @Override
    public List<TeamDtoReq> getAll() {
        List<TeamDtoReq> result = teamRepository.findAll().stream()
                .map(this::toDtoRes)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public TeamDtoReq update(Long id, TeamDtoReq teamDto) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        team.setName(teamDto.name());
        team.setName(teamDto.description());
        Team updatedTeam = teamRepository.save(team);
        return modelMapper.map(updatedTeam, TeamDtoReq.class);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    private TeamDtoReq toDtoRes(Team team) {
        return new TeamDtoReq(team.getName(),
                team.getDescription()
        );
    }
}
