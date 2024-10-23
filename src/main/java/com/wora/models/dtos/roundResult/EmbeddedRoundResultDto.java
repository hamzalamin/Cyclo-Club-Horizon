package com.wora.models.dtos.roundResult;

import com.wora.models.dtos.embeded.RoundResultIdDto;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record EmbeddedRoundResultDto(
        RoundResultIdDto id,
        @NotNull Duration duration,
        @NotNull Integer position
) {
}
