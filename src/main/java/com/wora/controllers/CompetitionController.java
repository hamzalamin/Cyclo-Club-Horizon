package com.wora.controllers;

import com.wora.models.dtos.competition.CompetitionDto;
import com.wora.models.dtos.competition.CreateCompetitionDto;
import com.wora.services.impl.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@RequestBody @Valid CreateCompetitionDto competitionDto){
        CompetitionDto competition = competitionService.create(competitionDto);
        return ResponseEntity.ok(competition);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDto> getCompetitionById(@PathVariable("id") Long id){
        CompetitionDto competition = competitionService.getById(id);
        return new ResponseEntity<CompetitionDto> (competition, HttpStatus.OK);
    }

}
