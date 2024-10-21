package com.wora.models.dtos.roundResult;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record UpdateRoundResultDto(
        @NotNull Duration duration,
        @NotNull Integer position
) {
}
