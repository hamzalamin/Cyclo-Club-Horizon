package com.wora.models.dtos.generalResult;

import jakarta.validation.constraints.Positive;

public record CreateGeneralResultDto(
        @Positive Long competitionId,
        @Positive Long riderId
) {
}
