package com.wora.controllers;

import com.wora.mappers.TeamMapper;
import com.wora.models.dtos.Team.CreateTeamDto;
import com.wora.models.dtos.Team.TeamDto;
import com.wora.models.dtos.Team.UpdateTeamDto;
import com.wora.models.entities.Team;
import com.wora.services.impl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams(){
        List<TeamDto> teams = teamService.getAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable("id") Long id){
        TeamDto team = teamService.getById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TeamDto> updateTeam(@PathVariable("id") Long id, @Validated @RequestBody UpdateTeamDto updateTeamDto){
        TeamDto team = teamService.update(id, updateTeamDto);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long id){
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
