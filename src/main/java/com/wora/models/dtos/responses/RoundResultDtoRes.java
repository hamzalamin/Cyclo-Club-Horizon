package com.wora.models.dtos.responses;

import com.wora.models.entities.Rider;
import com.wora.models.entities.Round;
import com.wora.models.entities.embeddables.RoundResultId;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record RoundResultDtoRes(
        RoundResultId id,
        Duration duration,
        Integer position,
        Round round,
        Rider rider
) {

}
