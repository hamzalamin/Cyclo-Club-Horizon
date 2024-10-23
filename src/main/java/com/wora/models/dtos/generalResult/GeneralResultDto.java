package com.wora.models.dtos.generalResult;

import com.wora.models.dtos.embeded.GeneralResultIdDto;
import com.wora.models.entities.Competition;
import com.wora.models.entities.Rider;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record GeneralResultDto(
        GeneralResultIdDto id,
        @NotNull Duration duration,
        @NotNull Integer position,
        Competition competition,
        Rider rider
) {
}
