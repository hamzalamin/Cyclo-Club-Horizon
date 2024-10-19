package com.wora.services.impl;

import com.wora.models.dtos.requests.RoundResultDtoReq;
import com.wora.models.dtos.responses.RoundResultDtoRes;
import com.wora.models.entities.Rider;
import com.wora.models.entities.Round;
import com.wora.models.entities.RoundResult;
import com.wora.models.entities.embeddables.RoundResultId;
import com.wora.repositories.RiderRepository;
import com.wora.repositories.RoundRepository;
import com.wora.repositories.RoundResultRepository;
import com.wora.services.IRoundResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundResultService implements IRoundResultService {
    @Autowired
    private RoundResultRepository roundResultRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Override
    public RoundResultDtoRes create(RoundResultDtoReq dto) {
        Long roundId = dto.roundId();
        Long riderId = dto.riderId();

        Round round = roundRepository.findById(roundId)
                .orElseThrow(() -> new RuntimeException("Round not found with id " + roundId));
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found with id " + riderId));

        RoundResult roundResult = new RoundResult(round, rider);
        RoundResult savedRoundResult = roundResultRepository.save(roundResult);
        return  modelMapper.map(savedRoundResult, RoundResultDtoRes.class);
    }

    @Override
    public RoundResultDtoRes getById(RoundResultId id) {
        RoundResult roundResult =  roundResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round result Id is not found"));
        return todoRes(roundResult);
    }

    @Override
    public List<RoundResultDtoRes> getAll() {
        List<RoundResultDtoRes> results = roundResultRepository.findAll().stream()
                .map(this::todoRes)
                .collect(Collectors.toList());
        return results;
    }

    @Override
    public RoundResultDtoReq update(RoundResultId id, RoundResultDtoReq roundResultDtoReq) {
        RoundResult existingRoundResult = roundResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round result with Id not found"));

        existingRoundResult.setDuration(roundResultDtoReq.duration());
        existingRoundResult.setPosition(roundResultDtoReq.position());
        RoundResult updatedRoundResult = roundResultRepository.save(existingRoundResult);
        return modelMapper.map(updatedRoundResult, RoundResultDtoReq.class);
    }


    @Override
    public void delete(RoundResultId id) {
        RoundResult roundResult = roundResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round result with Id not found"));

        roundResultRepository.delete(roundResult);
    }



    private RoundResultDtoRes todoRes(RoundResult roundResult){
        return new RoundResultDtoRes(roundResult.getId(),
                roundResult.getDuration(),
                roundResult.getPosition(),
                roundResult.getRound(),
                roundResult.getRider()
        );
    }
}
