package com.wora.controllers;

import com.wora.models.dtos.generalResult.CreateGeneralResultDto;
import com.wora.models.dtos.generalResult.GeneralResultDto;
import com.wora.models.entities.embeddables.GeneralResultId;
import com.wora.services.IGeneralResultService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/general-results")
public class GeneralResultController {

    @Autowired
    private IGeneralResultService generalResultService;

    @PostMapping
    public ResponseEntity<GeneralResultDto> createGeneralResul(@RequestBody @Valid CreateGeneralResultDto dto){
        return new ResponseEntity<>(generalResultService.create(dto), HttpStatus.OK);
    }

    @GetMapping("/{riderId}/{competition_id}")
    public ResponseEntity<GeneralResultDto> getGeneralResultById(@PathVariable("riderId") @Positive Long riderId, @PathVariable("competition_id") Long competitionId
    ){
        GeneralResultId id = new GeneralResultId(competitionId, riderId);
        GeneralResultDto generalResult = generalResultService.findById(id);
        return new ResponseEntity<>(generalResult , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GeneralResultDto>> getAllGeneralResult(){
        List<GeneralResultDto> generalResults = generalResultService.findAll();
        return new ResponseEntity<>(generalResults, HttpStatus.OK);
    }

    @DeleteMapping("/{riderId}/{competition_id}")
    public ResponseEntity<Void> deleteGeneralResult(@PathVariable("riderId") @Positive Long riderId, @PathVariable("competition_id") Long competitionId
    ){
        GeneralResultId id = new GeneralResultId(competitionId, riderId);
        generalResultService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
