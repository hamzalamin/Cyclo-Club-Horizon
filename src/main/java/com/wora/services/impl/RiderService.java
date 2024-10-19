package com.wora.services.impl;

import com.wora.models.dtos.requests.RiderDtoReq;
import com.wora.models.dtos.responses.RiderDtoRes;
import com.wora.models.entities.Rider;
import com.wora.models.entities.Team;
import com.wora.repositories.RiderRepository;
import com.wora.repositories.TeamRepository;
import com.wora.services.IRiderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiderService implements IRiderService {
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private  RiderRepository riderRepository;
    @Autowired
    private TeamRepository teamRepository;


    @Override
    public RiderDtoReq create(RiderDtoReq riderDto, RiderDtoRes riderDtoRes) {
        Long teamId = riderDtoRes.team().getId();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id " + teamId));
        Rider rider = modelMapper.map(riderDto, Rider.class);
        rider.setTeam(team);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDtoReq.class);
    }

    @Override
    public RiderDtoReq getById(Long id) {
        Rider rider = riderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rider not found"));
        return toDtoRes(rider);
    }

    @Override
    public List<RiderDtoReq> getAll() {
        List<RiderDtoReq> riders = riderRepository.findAll().stream()
                .map(this::toDtoRes)
                .collect(Collectors.toList());
        return riders;
    }

    @Override
    public RiderDtoReq update(Long id, RiderDtoReq riderDto) {
        Rider rider = riderRepository.findById(id).orElseThrow(() -> new RuntimeException("Rider not found"));
        rider.setfName(riderDto.fName());
        rider.setfName(riderDto.lName());
        rider.setfName(riderDto.nationality());
        Rider updateRider = riderRepository.save(rider);
        return modelMapper.map(updateRider, RiderDtoReq.class);
    }

    @Override
    public void delete(Long id) {
        riderRepository.deleteById(id);

    }

    private RiderDtoReq toDtoRes(Rider rider) {
        return new RiderDtoReq(rider.getfName(),
                rider.getlName(),
                rider.getBirthDate(),
                rider.getNationality()
//               rider.getTeam()
        );
    }

}
