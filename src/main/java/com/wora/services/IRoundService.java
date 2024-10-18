package com.wora.services;


import com.wora.models.dtos.requests.RoundDtoReq;

import java.util.List;

public interface IRoundService {
    RoundDtoReq create(RoundDtoReq roundDto);
    RoundDtoReq getById(Long id);
    List<RoundDtoReq> getAll();
    RoundDtoReq update(Long id, RoundDtoReq roundDto);
    void delete(Long id);
}
