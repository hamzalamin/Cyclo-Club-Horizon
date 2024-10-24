package com.wora.models.dtos.roundResult;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Duration;

public record UpdateRoundResultDto(
        @NotNull Duration duration,
        @Positive Integer position,
        @Positive Long roundId,
        @Positive Long riderId
) {
}
