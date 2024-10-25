package com.wora.controllers;

import com.wora.models.dtos.generalResult.CreateGeneralResultDto;
import com.wora.models.dtos.generalResult.GeneralResultDto;
import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.embeddables.GeneralResultId;
import com.wora.models.entities.embeddables.RoundResultId;
import com.wora.services.IGeneralResultService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/general-results")
public class GeneralResultController {

    @Autowired
    private IGeneralResultService generalResultService;

    @PostMapping
    public ResponseEntity<GeneralResultDto> createGeneralResul(@RequestBody CreateGeneralResultDto dto){
        return new ResponseEntity<>(generalResultService.create(dto), HttpStatus.OK);
    }

    @GetMapping("/{riderId}/{competition_id}")
    public ResponseEntity<GeneralResultDto> getGeneralResultById(@PathVariable("riderId") @Positive Long riderId, @PathVariable("competition_id") Long competitionId
    ){
        GeneralResultId id = new GeneralResultId(competitionId, riderId);
        GeneralResultDto generalResult = generalResultService.findById(id);
        return new ResponseEntity<>(generalResult , HttpStatus.OK);
    }
}
