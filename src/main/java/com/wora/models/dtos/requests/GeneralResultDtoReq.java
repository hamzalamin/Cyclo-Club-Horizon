package com.wora.models.dtos.requests;

import com.wora.models.entities.embeddables.GeneralResultId;

import java.time.Duration;

public record GeneralResultDtoReq(
        GeneralResultId id,
        Duration generalTime,
        Integer range
) {
}
