package com.wora.models.dtos.generalResult;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record CreateGeneralResultDto(
        @NotNull Duration duration,
        @NotNull Integer position
) {
}
