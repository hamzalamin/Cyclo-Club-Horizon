package com.wora.models.dtos.requests;
import com.wora.models.entities.embeddables.RoundResultId;

import java.time.Duration;

public record RoundResultDtoReq(
        Long riderId,
        Long roundId,
        Duration duration,
        Integer position
) {
}
