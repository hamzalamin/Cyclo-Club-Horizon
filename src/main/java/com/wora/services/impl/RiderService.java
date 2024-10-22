package com.wora.services.impl;

import com.wora.mappers.RiderMapper;
import com.wora.models.dtos.rider.CreateRiderDto;
import com.wora.models.dtos.rider.RiderDto;
import com.wora.models.dtos.rider.UpdateRiderDto;
import com.wora.models.entities.Rider;
import com.wora.models.entities.Team;
import com.wora.repositories.RiderRepository;
import com.wora.repositories.TeamRepository;
import com.wora.services.IRiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiderService implements IRiderService {
    @Autowired
    private  RiderRepository riderRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RiderMapper riderMapper;


    @Override
    public RiderDto create(CreateRiderDto riderDto) {
        Long teamId = riderDto.teamId();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id " + teamId));
        Rider rider = riderMapper.toEntity(riderDto);
        rider.setTeam(team);
        Rider savedRider = riderRepository.save(rider);
        return riderMapper.toDto(savedRider);
    }

    @Override
    public RiderDto getById(Long id) {
        Rider rider = riderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rider not found"));
        return riderMapper.toDto(rider);
    }

    @Override
    public List<RiderDto> getAll() {
        List<RiderDto> riders = riderRepository.findAll().stream()
                .map(riderMapper::toDto)
                .collect(Collectors.toList());
        return riders;
    }

    @Override
    public RiderDto update(Long id, UpdateRiderDto riderDto) {
        Rider rider = riderRepository.findById(id).orElseThrow(() -> new RuntimeException("Rider not found"));
        riderMapper.toEntity(riderDto);
        Rider updateRider = riderRepository.save(rider);
        return riderMapper.toDto(updateRider);
    }

    @Override
    public void delete(Long id) {
        riderRepository.deleteById(id);

    }
}
