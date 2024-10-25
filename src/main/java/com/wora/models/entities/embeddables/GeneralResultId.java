package com.wora.models.entities.embeddables;

import jakarta.validation.constraints.Positive;

public record GeneralResultId(
        @Positive Long riderId,
        @Positive Long competitionId
) {
}
