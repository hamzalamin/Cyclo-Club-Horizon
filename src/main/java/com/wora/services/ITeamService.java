package com.wora.services;

import com.wora.models.dtos.requests.TeamDtoReq;
import com.wora.models.dtos.responses.TeamDtoRes;
import java.util.List;

public interface ITeamService {
    TeamDtoReq create(TeamDtoReq teamDto);
    TeamDtoReq getById(Long id);
    List<TeamDtoReq> getAll();
    TeamDtoReq update(Long id, TeamDtoReq teamDto);
    void delete(Long id);
}
