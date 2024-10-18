package com.wora.services.impl;

import com.wora.models.dtos.requests.TeamDtoReq;
import com.wora.models.dtos.responses.RiderDtoReq;
import com.wora.models.entities.Rider;
import com.wora.models.entities.Team;
import com.wora.repositories.RiderRepository;
import com.wora.services.IRiderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiderService implements IRiderService {
    private final ModelMapper modelMapper;
    private final RiderRepository riderRepository;

    public RiderService(ModelMapper modelMapper, RiderRepository riderRepository) {
        this.modelMapper = modelMapper;
        this.riderRepository = riderRepository;
    }

    @Override
    public RiderDtoReq create(RiderDtoReq riderDto) {
        Rider rider = modelMapper.map(riderDto, Rider.class);
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
//                rider.getTeam()
        );
    }
}
