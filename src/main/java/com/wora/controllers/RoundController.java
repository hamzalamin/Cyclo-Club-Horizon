package com.wora.controllers;


import com.wora.models.dtos.round.CreateRoundDto;
import com.wora.models.dtos.round.RoundDto;
import com.wora.services.impl.RoundService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stages")
public class RoundController {

    @Autowired
    private RoundService roundService;

    @PostMapping
    public ResponseEntity<RoundDto> createRound(@RequestBody @Valid CreateRoundDto roundDto){
        return new ResponseEntity<>(roundService.create(roundDto), HttpStatus.OK);
    }


}