package com.wora.services.impl;

import com.wora.models.dtos.requests.CompetitionDtoReq;
import com.wora.models.entities.Competition;
import com.wora.repositories.CompetitionRepository;
import com.wora.services.ICompetitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionService implements ICompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CompetitionDtoReq create(CompetitionDtoReq competitionDto) {
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        System.out.println(competition);
        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionDtoReq.class);
    }

    @Override
    public CompetitionDtoReq getById(Long id) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        return modelMapper.map(competition, CompetitionDtoReq.class);
    }

    @Override
    public List<CompetitionDtoReq> getAll() {
        return competitionRepository.findAll().stream()
                .map(competition -> modelMapper.map(competition, CompetitionDtoReq.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompetitionDtoReq update(Long id, CompetitionDtoReq competitionDto) {
        Competition competition = competitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Competition not found"));
        competition.setName(competitionDto.name());
        competition.setName(competitionDto.name());
        competition.setLocation(competitionDto.location());
        competition.setStartDate(competitionDto.startDate());
        competition.setEndDate(competitionDto.endDate());
        Competition updatedCompetition = competitionRepository.save(competition);
        return modelMapper.map(updatedCompetition, CompetitionDtoReq.class);
    }

    @Override
    public void delete(Long id) {
        competitionRepository.deleteById(id);
    }
}
