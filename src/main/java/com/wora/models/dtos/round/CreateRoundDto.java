package com.wora.models.dtos.round;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateRoundDto(
        @NotNull @Positive Integer stageNumber,
        @NotNull LocalDate startDte,
        @NotNull LocalDate endDte,
        @Positive Long competitionId
) {
}
