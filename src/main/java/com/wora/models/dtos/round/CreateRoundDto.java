package com.wora.models.dtos.round;

import com.wora.models.entities.Competition;
import com.wora.models.entities.RoundResult;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateRoundDto(
        @NotNull Integer stageNumber,
        @NotNull LocalDate startDte,
        @NotNull LocalDate endDte
) {
}
