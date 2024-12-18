package com.wora.services.impl;

import com.wora.exception.EntityNotFoundException;
import com.wora.mappers.RoundResultMapper;
import com.wora.models.dtos.roundResult.CreateRoundResultDto;
import com.wora.models.dtos.roundResult.RoundResultDto;
import com.wora.models.entities.*;
import com.wora.models.entities.embeddables.GeneralResultId;
import com.wora.models.entities.embeddables.RoundResultId;
import com.wora.repositories.RiderRepository;
import com.wora.repositories.RoundRepository;
import com.wora.repositories.RoundResultRepository;
import com.wora.services.IRoundResultService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public RoundResultDto create(CreateRoundResultDto dto) {
        System.out.println("tprinta hna ola nch3el mea inaaak " + dto);
        Long roundId = dto.roundId();
        Long riderId = dto.riderId();

        Round round = roundRepository.findById(roundId)
                .orElseThrow(() -> new RuntimeException("Round not found with id " + roundId));
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found with id " + riderId));

        if (!isRiderExistInCompetition(rider, round)){
            throw new RuntimeException("rider is not exist in this Competition");
        }

        if (round.getIsClosed()){
            throw new RuntimeException("Cannot add round result; the round is closed.");
        }

        RoundResult roundResult = roundResultMapper.toEntity(dto);
        roundResult.setRider(rider);
        roundResult.setRound(round);
        RoundResult savedRoundResult = roundResultRepository.save(roundResult);
        return roundResultMapper.toDto(savedRoundResult);
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
    @Transactional
    public void delete(RoundResultId id) {
        if (!roundResultRepository.existsById(id))
            throw new EntityNotFoundException("General result ", id);
        roundResultRepository.deleteByRoundResultId(id.riderId(), id.roundId());
    }

    private boolean isRiderExistInCompetition(Rider rider , Round round){
        return rider.getGeneralResults().stream()
                .map(GeneralResult::getCompetition)
                .anyMatch(competition -> round.getCompetition().getId().equals(competition.getId()));
    }

}
