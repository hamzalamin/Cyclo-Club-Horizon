package com.wora.controllers;

import com.wora.models.dtos.rider.CreateRiderDto;
import com.wora.models.dtos.rider.RiderDto;
import com.wora.services.impl.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cyclists")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @PostMapping
    public ResponseEntity<RiderDto> createRider(@RequestBody CreateRiderDto createRiderDto){
        RiderDto createRider = riderService.create(createRiderDto);
        return ResponseEntity.ok(createRider);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiderDto> getRiderById(@PathVariable("id") Long id){
        RiderDto rider = riderService.getById(id);
        return new ResponseEntity<>(rider, HttpStatus.OK);
    }



}
