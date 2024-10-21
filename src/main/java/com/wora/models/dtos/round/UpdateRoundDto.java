package com.wora.models.dtos.round;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateRoundDto(
        @NotNull Integer stageNumber,
        @NotNull LocalDate startDte,
        @NotNull LocalDate endDte
) {
}
