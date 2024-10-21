package com.wora.services;

import com.wora.models.dtos.generalResult.CreateGeneralResultDto;
import com.wora.models.dtos.generalResult.GeneralResultDto;

public interface IGeneralResultService {
    GeneralResultDto create(CreateGeneralResultDto dto);
}
