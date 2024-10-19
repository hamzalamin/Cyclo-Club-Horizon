package com.wora.services;
import com.wora.models.dtos.requests.RiderDtoReq;
import com.wora.models.dtos.responses.RiderDtoRes;

import java.util.List;

public interface IRiderService {
    RiderDtoReq create(RiderDtoReq riderDto, RiderDtoRes riderDtoRes);
    RiderDtoReq getById(Long id);
    List<RiderDtoReq> getAll();
    RiderDtoReq update(Long id, RiderDtoReq riderDto);
    void delete(Long id);


}
