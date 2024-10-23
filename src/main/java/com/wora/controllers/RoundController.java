package com.wora.controllers;


import com.wora.models.dtos.round.CreateRoundDto;
import com.wora.models.dtos.round.RoundDto;
import com.wora.models.dtos.round.UpdateRoundDto;
import com.wora.services.impl.RoundService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stages")
public class RoundController {

    @Autowired
    private RoundService roundService;

    @PostMapping
    public ResponseEntity<RoundDto> createRound(@RequestBody @Valid CreateRoundDto roundDto){
        return new ResponseEntity<>(roundService.create(roundDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoundDto>> getAllRounds(){
        List<RoundDto> rounds = roundService.getAll();
        return new ResponseEntity<>(rounds, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoundDto> getRoundById(@PathVariable("id") Long id){
        return new ResponseEntity<>(roundService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoundDto> updateRound(@PathVariable("id") Long id, @RequestBody @Valid UpdateRoundDto updateRoundDto){
        return new ResponseEntity<>(roundService.update(id, updateRoundDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRound(@PathVariable("id") Long id){
        roundService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
