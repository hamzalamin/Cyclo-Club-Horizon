package com.wora.services.impl;

import com.wora.mappers.RoundMapper;
import com.wora.models.dtos.round.CreateRoundDto;
import com.wora.models.dtos.round.RoundDto;
import com.wora.models.dtos.round.UpdateRoundDto;
import com.wora.models.entities.Competition;
import com.wora.models.entities.Round;
import com.wora.repositories.CompetitionRepository;
import com.wora.repositories.RoundRepository;
import com.wora.services.IRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundService implements IRoundService {
    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private RoundMapper roundMapper;
    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public RoundDto create(CreateRoundDto roundDto) {
        Long competitionId = roundDto.competitionId();
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("competition with this id " + competitionId +" is not found"));
        Round round = roundMapper.toEntity(roundDto);
        round.setCompetition(competition);
        Round savedRound = roundRepository.save(round);
        return roundMapper.toDto(savedRound);
    }

    @Override
    public RoundDto getById(Long id) {
        Round round = roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round not found"));
        return roundMapper.toDto(round);
    }

    @Override
    public List<RoundDto> getAll() {
        return roundRepository.findAll().stream()
                .map(roundMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoundDto update(Long id, UpdateRoundDto roundDto) {
        Round round = roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round not found"));
        roundMapper.toEntity(roundDto);
        Round updatedRound = roundRepository.save(round);
        return roundMapper.toDto(updatedRound);
    }

    @Override
    public void delete(Long id) {
        roundRepository.deleteById(id);
    }

}
