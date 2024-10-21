package com.wora.models.dtos.generalResult;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record UpdateGeneralResultDto(
        @NotNull Duration duration,
        @NotNull Integer position
) {
}
