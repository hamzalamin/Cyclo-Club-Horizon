package com.wora.services;

import com.wora.models.dtos.CompetitionDto;

import java.util.List;

public interface ICompetitionService {
    CompetitionDto create(CompetitionDto competitionDto);
    CompetitionDto getById(Long id);
    List<CompetitionDto> getAll();
    CompetitionDto update(Long id, CompetitionDto competitionDto);
    void delete(Long id);

}
