package com.wora.services;

import com.wora.models.dtos.requests.GeneralResultDtoReq;
import com.wora.models.dtos.responses.GeneralResultDtoRes;

public interface IGeneralResultService {
    public GeneralResultDtoRes create(GeneralResultDtoReq dto);
}
