package com.wora.models.dtos.round;

import com.wora.models.dtos.competition.EmbeddedCompetitionDto;
import com.wora.models.entities.RoundResult;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record RoundDto(
        @Positive Long id,
        @NotNull Integer stageNumber,
        @NotNull LocalDate startDte,
        @NotNull LocalDate endDte,
        EmbeddedCompetitionDto competition,
        List<RoundResult> roundResults
) {
}
