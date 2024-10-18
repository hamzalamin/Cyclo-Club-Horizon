package com.wora.services;

import com.wora.models.dtos.requests.CompetitionDtoReq;

import java.util.List;

public interface ICompetitionService {
    CompetitionDtoReq create(CompetitionDtoReq competitionDto);
    CompetitionDtoReq getById(Long id);
    List<CompetitionDtoReq> getAll();
    CompetitionDtoReq update(Long id, CompetitionDtoReq competitionDto);
    void delete(Long id);
}
