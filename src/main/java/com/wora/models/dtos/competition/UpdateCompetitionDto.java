package com.wora.models.dtos.competition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateCompetitionDto(
        @NotBlank String name,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotBlank String location,
        @NotNull Boolean isClosed

) {
}
