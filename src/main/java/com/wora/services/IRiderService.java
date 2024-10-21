package com.wora.services;
import com.wora.models.dtos.rider.CreateRiderDto;
import com.wora.models.dtos.rider.RiderDto;
import com.wora.models.dtos.rider.UpdateRiderDto;

import java.util.List;

public interface IRiderService {
    RiderDto create(CreateRiderDto riderDto);
    RiderDto getById(Long id);
    List<RiderDto> getAll();
    RiderDto update(Long id, UpdateRiderDto riderDto);
    void delete(Long id);


}
