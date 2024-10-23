package com.wora.models.dtos.competition;

import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.Round;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record EmbeddedCompetition(
        @Positive Long id,
        @NotBlank String name,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotBlank String location
) {
}
