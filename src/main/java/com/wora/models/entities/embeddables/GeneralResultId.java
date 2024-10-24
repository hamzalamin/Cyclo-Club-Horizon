package com.wora.models.entities.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

@Embeddable
public record GeneralResultId(
        @Positive Long riderId,
        @Positive Long competitionId
) {
}
