package com.wora.services;

import com.wora.models.dtos.generalResult.CreateGeneralResultDto;
import com.wora.models.dtos.generalResult.GeneralResultDto;
import com.wora.models.entities.embeddables.GeneralResultId;

import java.util.List;

public interface IGeneralResultService {
    GeneralResultDto create(CreateGeneralResultDto dto);
    GeneralResultDto findById(GeneralResultId id);
    List<GeneralResultDto> findAll();
    void delete(GeneralResultId id);
}
