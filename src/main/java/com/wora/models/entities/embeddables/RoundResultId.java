package com.wora.models.entities.embeddables;

import jakarta.validation.constraints.Positive;

public record RoundResultId(
        @Positive Long riderId,
        @Positive Long roundId
) {
}
