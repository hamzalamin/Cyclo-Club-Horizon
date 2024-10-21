package com.wora.models.dtos.generalResult;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Duration;

public record CreateGeneralResultDto(
        @NotNull Duration duration,
        @NotNull Integer position,
        @Positive Long competitionId,
        @Positive Long riderId
) {
}
