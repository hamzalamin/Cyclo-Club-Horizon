package com.wora.services;

import com.wora.models.dtos.roundResult.CreateRoundResultDto;
import com.wora.models.dtos.roundResult.RoundResultDto;
import com.wora.models.dtos.roundResult.UpdateRoundResultDto;
import com.wora.models.entities.embeddables.RoundResultId;

import java.util.List;

public interface IRoundResultService {
    RoundResultDto create(CreateRoundResultDto dto);
    RoundResultDto getById(RoundResultId id);
    List<RoundResultDto> getAll();
    RoundResultDto update(RoundResultId id, UpdateRoundResultDto dto);
    void delete(RoundResultId id);
}
