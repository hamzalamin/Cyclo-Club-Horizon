package com.wora.models.dtos.round;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateRoundDto(
        @NotNull @Positive Integer stageNumber,
        @NotNull @Future LocalDate startDte,
        @NotNull @Future LocalDate endDte,
        @NotNull Boolean isClosed,
        @Positive Long competitionId
) {
}
