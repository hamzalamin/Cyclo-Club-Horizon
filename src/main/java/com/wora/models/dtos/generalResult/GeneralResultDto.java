package com.wora.models.dtos.generalResult;

import com.wora.models.dtos.competition.EmbeddedCompetitionDto;
import com.wora.models.dtos.embeded.GeneralResultIdDto;
import com.wora.models.entities.Rider;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record GeneralResultDto(
        GeneralResultIdDto id,
        @NotNull Duration duration,
        @NotNull Integer position,
        @NotNull EmbeddedCompetitionDto competition,
        Rider rider
) {
}
