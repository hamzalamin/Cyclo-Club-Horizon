package com.wora.services;

import com.wora.models.dtos.requests.RoundResultDtoReq;
import com.wora.models.dtos.responses.RoundResultDtoRes;

import java.util.List;

public interface IRoundResultService {
    RoundResultDtoReq create(RoundResultDtoReq roundResultDtoReq);
    RoundResultDtoRes getById(Long id);
    List<RoundResultDtoReq> getAll();
    RoundResultDtoReq update(Long id, RoundResultDtoReq roundResultDtoReq);
    void delete(Long id);
}
