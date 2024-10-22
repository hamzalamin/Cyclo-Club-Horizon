package com.wora.models.dtos.roundResult;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Duration;

public record CreateRoundResultDto(
        @NotNull Duration duration,
        @NotNull Integer position,
        @Positive Long roundId,
        @Positive Long riderId
) {
}
