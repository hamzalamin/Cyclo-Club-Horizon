package com.wora.controllers;

import com.wora.models.dtos.roundResult.CreateRoundResultDto;
import com.wora.models.dtos.roundResult.RoundResultDto;
import com.wora.models.entities.embeddables.RoundResultId;
import com.wora.services.IRoundResultService;
import com.wora.services.impl.RoundResultService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stage-results")
public class RoundResultController {
    @Autowired
    private IRoundResultService roundResultService;

    @PostMapping
    public ResponseEntity<RoundResultDto> createRoundResult(@RequestBody CreateRoundResultDto createRoundResultDto){
        return new ResponseEntity<>(roundResultService.create(createRoundResultDto), HttpStatus.OK);
    }

    @GetMapping("/{riderId}/{roundId}")
    public ResponseEntity<RoundResultDto> getRoundResultById(
            @PathVariable("riderId") @Positive Long riderId,
            @PathVariable("roundId") @Positive Long roundId)
    {
        RoundResultId id = new RoundResultId(riderId, roundId);
        RoundResultDto roundResult = roundResultService.getById(id);
        return new ResponseEntity<>(roundResult, HttpStatus.OK);
    }



}