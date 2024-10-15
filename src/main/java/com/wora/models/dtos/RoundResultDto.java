package com.wora.models.dtos;

import com.wora.models.entities.Rider;
import com.wora.models.entities.Round;
import com.wora.models.entities.embeddables.RoundResultId;

import java.time.Duration;

public record RoundResultDto(
        RoundResultId id,
        Duration duration,
        Integer position,
        Round round,
        Rider rider
) {
}
