package com.wora.services.impl;

import com.wora.models.dtos.requests.RoundResultDtoReq;
import com.wora.models.dtos.responses.RoundResultDtoRes;
import com.wora.services.IRoundResultService;

import java.util.List;

public class RoundResultService implements IRoundResultService {
    @Override
    public RoundResultDtoReq create(RoundResultDtoReq roundResultDtoReq) {
        return null;
    }

    @Override
    public RoundResultDtoRes getById(Long id) {
        return null;
    }

    @Override
    public List<RoundResultDtoReq> getAll() {
        return List.of();
    }

    @Override
    public RoundResultDtoReq update(Long id, RoundResultDtoReq roundResultDtoReq) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
