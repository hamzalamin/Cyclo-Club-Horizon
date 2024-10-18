package com.wora.models.dtos.requests;
import com.wora.models.entities.embeddables.RoundResultId;

import java.time.Duration;

public record RoundResultDtoReq(
        RoundResultId id,
        Duration duration,
        Integer position
) {
}
