package com.wora.controllers;

import com.wora.models.dtos.rider.CreateRiderDto;
import com.wora.models.dtos.rider.RiderDto;
import com.wora.models.dtos.rider.UpdateRiderDto;
import com.wora.services.IRiderService;
import com.wora.services.impl.RiderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cyclists")
public class RiderController {

    @Autowired
    private IRiderService riderService;

    @PostMapping
    public ResponseEntity<RiderDto> createRider(@RequestBody @Valid CreateRiderDto createRiderDto){
        RiderDto createRider = riderService.create(createRiderDto);
        return ResponseEntity.ok(createRider);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiderDto> getRiderById(@PathVariable("id") Long id){
        RiderDto rider = riderService.getById(id);
        return new ResponseEntity<>(rider, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RiderDto>> getAllRiders(){
        List<RiderDto> riders = riderService.getAll();
        return new ResponseEntity<>(riders, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiderDto> updateRider(@PathVariable("id") Long id, @RequestBody @Valid UpdateRiderDto updateRiderDto){
        RiderDto rider = riderService.update(id, updateRiderDto);
        return new ResponseEntity<>(rider, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRider(@PathVariable("id") Long id){
        riderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
