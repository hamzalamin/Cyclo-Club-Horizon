package com.wora.services;

import com.wora.models.dtos.requests.RoundResultDtoReq;
import com.wora.models.dtos.responses.RoundResultDtoRes;
import com.wora.models.entities.embeddables.RoundResultId;

import java.util.List;

public interface IRoundResultService {
    RoundResultDtoReq create(RoundResultDtoReq roundResultDtoReq, RoundResultDtoRes roundResultDtoRes);
    RoundResultDtoRes getById(RoundResultId id);
    List<RoundResultDtoRes> getAll();
    RoundResultDtoReq update(RoundResultId id, RoundResultDtoReq roundResultDtoReq);
    void delete(RoundResultId id);
}
