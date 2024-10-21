package com.wora.models.dtos.roundResult;

import com.wora.models.dtos.embeded.RoundResultIdDto;
import com.wora.models.entities.Rider;
import com.wora.models.entities.Round;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record RoundResultDto(
        RoundResultIdDto id,
        @NotNull Duration duration,
        @NotNull Integer position,
        Round round,
        Rider rider
) {
}
