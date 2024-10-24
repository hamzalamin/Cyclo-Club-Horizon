package com.wora.controllers;

import com.wora.models.dtos.roundResult.CreateRoundResultDto;
import com.wora.models.dtos.roundResult.RoundResultDto;
import com.wora.services.IRoundResultService;
import com.wora.services.impl.RoundResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stage-results")
public class RoundResultController {
    @Autowired
    private IRoundResultService roundResultService;

    @PostMapping
    public ResponseEntity<RoundResultDto> createRoundResult(@RequestBody CreateRoundResultDto createRoundResultDto){
        System.out.println(roundResultService);
        return new ResponseEntity<>(roundResultService.create(createRoundResultDto), HttpStatus.OK);
    }


}
