package com.wora.services.impl;

import com.wora.models.dtos.CompetitionDto;
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
    public CompetitionDto create(CompetitionDto competitionDto) {
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        System.out.println(competition);
        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionDto.class);
    }

    @Override
    public CompetitionDto getById(Long id) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        return modelMapper.map(competition, CompetitionDto.class);
    }

    @Override
    public List<CompetitionDto> getAll() {
        return competitionRepository.findAll().stream()
                .map(competition -> modelMapper.map(competition, CompetitionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompetitionDto update(Long id, CompetitionDto competitionDto) {
        Competition competition = competitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Competition not found"));
        competition.setName(competitionDto.name());
        competition.setName(competitionDto.name());
        competition.setLocation(competitionDto.location());
        competition.setStartDate(competitionDto.startDate());
        competition.setEndDate(competitionDto.endDate());
        Competition updatedCompetition = competitionRepository.save(competition);
        return modelMapper.map(updatedCompetition, CompetitionDto.class);
    }

    @Override
    public void delete(Long id) {
        competitionRepository.deleteById(id);
    }
}
