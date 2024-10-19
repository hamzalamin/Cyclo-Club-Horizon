package com.wora.services.impl;

import com.wora.models.dtos.requests.RoundDtoReq;
import com.wora.models.entities.Round;
import com.wora.repositories.RoundRepository;
import com.wora.services.IRoundService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundService implements IRoundService {
    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoundDtoReq create(RoundDtoReq roundDto) {
        Round round = modelMapper.map(roundDto, Round.class);
        Round savedRound = roundRepository.save(round);
        return modelMapper.map(savedRound, RoundDtoReq.class);
    }

    @Override
    public RoundDtoReq getById(Long id) {
        Round round = roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round not found"));
        return toDtoRes(round);
    }

    @Override
    public List<RoundDtoReq> getAll() {
        return roundRepository.findAll().stream()
                .map(this::toDtoRes)
                .collect(Collectors.toList());
    }

    @Override
    public RoundDtoReq update(Long id, RoundDtoReq roundDto) {
        Round round = roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round not found"));
        round.setStageNumber(roundDto.stageNumber());
        round.setStartDte(roundDto.startDte());
        round.setEndDte(roundDto.endDte());
        Round updatedRound = roundRepository.save(round);
        return modelMapper.map(updatedRound, RoundDtoReq.class);
    }

    @Override
    public void delete(Long id) {
        roundRepository.deleteById(id);
    }

    private RoundDtoReq toDtoRes(Round round) {
        return modelMapper.map(round, RoundDtoReq.class);
    }
}
