package com.wora.services;


import com.wora.models.dtos.requests.RoundDtoReq;
import com.wora.models.dtos.round.CreateRoundDto;
import com.wora.models.dtos.round.RoundDto;
import com.wora.models.dtos.round.UpdateRoundDto;

import java.util.List;

public interface IRoundService {
    RoundDto create(CreateRoundDto roundDto);
    RoundDto getById(Long id);
    List<RoundDto> getAll();
    RoundDto update(Long id, UpdateRoundDto roundDto);
    void delete(Long id);
}
