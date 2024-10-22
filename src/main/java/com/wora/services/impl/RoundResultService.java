package com.wora.services.impl;

import com.wora.mappers.RoundResultMapper;
import com.wora.models.dtos.roundResult.CreateRoundResultDto;
import com.wora.models.dtos.roundResult.RoundResultDto;
import com.wora.models.dtos.roundResult.UpdateRoundResultDto;
import com.wora.models.entities.Rider;
import com.wora.models.entities.Round;
import com.wora.models.entities.RoundResult;
import com.wora.models.entities.embeddables.RoundResultId;
import com.wora.repositories.RiderRepository;
import com.wora.repositories.RoundRepository;
import com.wora.repositories.RoundResultRepository;
import com.wora.services.IRoundResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundResultService implements IRoundResultService {
    @Autowired
    private RoundResultRepository roundResultRepository;

    @Autowired
    private RoundResultMapper roundResultMapper;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Override
    public RoundResultDto create(CreateRoundResultDto dto) {
        Long roundId = dto.roundId();
        Long riderId = dto.riderId();

        Round round = roundRepository.findById(roundId)
                .orElseThrow(() -> new RuntimeException("Round not found with id " + roundId));
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found with id " + riderId));

        RoundResult roundResult = roundResultMapper.toEntity(dto);
        roundResult.setRound(round);
        roundResult.setRider(rider);
        RoundResult savedRoundResult = roundResultRepository.save(roundResult);
        return  roundResultMapper.toDto(savedRoundResult);
    }

    @Override
    public RoundResultDto getById(RoundResultId id) {
        RoundResult roundResult =  roundResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round result Id is not found"));
        return roundResultMapper.toDto(roundResult);
    }

    @Override
    public List<RoundResultDto> getAll() {
        List<RoundResultDto> results = roundResultRepository.findAll().stream()
                .map(roundResultMapper::toDto)
                .collect(Collectors.toList());
        return results;
    }

    @Override
    public RoundResultDto update(RoundResultId id, UpdateRoundResultDto dto) {
        RoundResult existingRoundResult = roundResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round result with Id not found"));

        roundResultMapper.toEntity(dto);
        RoundResult updatedRoundResult = roundResultRepository.save(existingRoundResult);
        return roundResultMapper.toDto(updatedRoundResult);
    }


    @Override
    public void delete(RoundResultId id) {
        RoundResult roundResult = roundResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round result with Id not found"));

        roundResultRepository.delete(roundResult);
    }

}
