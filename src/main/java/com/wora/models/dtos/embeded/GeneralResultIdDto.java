package com.wora.models.dtos.embeded;

import jakarta.validation.constraints.Positive;

public record GeneralResultIdDto(
        @Positive Long riderId,
        @Positive Long competitionId
) {
}
