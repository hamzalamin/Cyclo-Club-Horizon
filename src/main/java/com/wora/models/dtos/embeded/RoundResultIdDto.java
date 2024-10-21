package com.wora.models.dtos.embeded;

import jakarta.validation.constraints.Positive;

public record RoundResultIdDto(
        @Positive Long riderId,
        @Positive Long roundId
) {
}
