package com.wora.services.impl;

import com.wora.mappers.CompetitionMapper;
import com.wora.models.dtos.competition.CompetitionDto;
import com.wora.models.dtos.competition.CreateCompetitionDto;
import com.wora.models.dtos.competition.UpdateCompetitionDto;
import com.wora.models.entities.Competition;
import com.wora.repositories.CompetitionRepository;
import com.wora.services.ICompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionService implements ICompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CompetitionMapper competitionMapper;


    @Override
    public CompetitionDto create(CreateCompetitionDto competitionDto){
        Competition competition = competitionMapper.toEntity(competitionDto);
        Competition savedCompetition = competitionRepository.save(competition);
        return competitionMapper.toDto(savedCompetition);
    }

    @Override
    public CompetitionDto getById(Long id) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        return competitionMapper.toDto(competition);
    }

    @Override
    public List<CompetitionDto> getAll(){
        List<CompetitionDto> result = competitionRepository.findAll().stream()
                .map(competitionMapper::toDto)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public CompetitionDto update(Long id, UpdateCompetitionDto dto) {
        Competition competition = competitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Competition not found"));
        competition.setName(dto.name());
        competition.setStartDate(dto.startDate());
        competition.setEndDate(dto.endDate());
        competition.setLocation(dto.location());
        Competition updatedCompetition = competitionRepository.save(competition);
        return competitionMapper.toDto(updatedCompetition);
    }

    @Override
    public void delete(Long id) {
        competitionRepository.deleteById(id);
    }

}
