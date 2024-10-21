package com.wora.services.impl;

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
    private ModelMapper modelMapper;


    @Override
    public CreateCompetitionDto create(CreateCompetitionDto dto){
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionDtoReq.class);
    }

    @Override
    public CompetitionDto getById(Long id) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        return toDtoRes(competition);
    }

    @Override
    public List<CompetitionDto> getAll(){
        List<CompetitionDtoReq> result = competitionRepository.findAll().stream()
                .map(this::toDtoRes)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public UpdateCompetitionDto update(Long id, UpdateCompetitionDto dto) {
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

    private CompetitionDtoReq toDtoRes(Competition competition) {
        return new CompetitionDtoReq(competition.getName(),
                competition.getStartDate(),
                competition.getEndDate(),
                competition.getLocation()
        );
    }
}
