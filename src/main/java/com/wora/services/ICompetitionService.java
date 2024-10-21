package com.wora.services;

import com.wora.models.dtos.competition.CompetitionDto;
import com.wora.models.dtos.competition.CreateCompetitionDto;
import com.wora.models.dtos.competition.UpdateCompetitionDto;

import java.util.List;

public interface ICompetitionService {
    CompetitionDto create(CreateCompetitionDto competitionDto);
    CompetitionDto getById(Long id);
    List<CompetitionDto> getAll();
    CompetitionDto update(Long id, UpdateCompetitionDto dto);
    void delete(Long id);
}
