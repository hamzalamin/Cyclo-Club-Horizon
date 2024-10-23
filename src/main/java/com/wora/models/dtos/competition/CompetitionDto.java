package com.wora.models.dtos.competition;

import com.wora.models.dtos.generalResult.EmbeddedGeneralResultDto;
import com.wora.models.dtos.round.EmbeddedRoundDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record CompetitionDto(
        @Positive Long id,
        @NotBlank String name,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotBlank String location,
        List<EmbeddedGeneralResultDto> generalResults,
        List<EmbeddedRoundDto> rounds
) {

}
