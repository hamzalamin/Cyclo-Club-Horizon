package com.wora.models.dtos.roundResult;

import com.wora.models.dtos.embeded.RoundResultIdDto;
import com.wora.models.dtos.rider.EmbeddedRiderDto;
import com.wora.models.dtos.round.EmbeddedRoundDto;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record RoundResultDto(
        RoundResultIdDto id,
        @NotNull Duration duration,
        @NotNull Integer position,
        EmbeddedRoundDto round,
        EmbeddedRiderDto rider
) {
}
