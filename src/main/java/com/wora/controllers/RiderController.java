package com.wora.controllers;

import com.wora.models.dtos.rider.CreateRiderDto;
import com.wora.models.dtos.rider.RiderDto;
import com.wora.services.impl.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
