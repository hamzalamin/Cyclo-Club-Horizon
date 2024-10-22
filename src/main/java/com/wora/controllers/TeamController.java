package com.wora.controllers;

import com.wora.mappers.TeamMapper;
import com.wora.models.dtos.Team.CreateTeamDto;
import com.wora.models.dtos.Team.TeamDto;
import com.wora.services.impl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/api/v1/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody CreateTeamDto createTeamDto){
        TeamDto createdTeam = teamService.create(createTeamDto);
        return ResponseEntity.ok(createdTeam);
    }

}
