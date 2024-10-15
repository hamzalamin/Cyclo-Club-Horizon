package com.wora.models.entities.embeddables;

import jakarta.persistence.Embeddable;

@Embeddable
public record GeneralResultId(Long riderId, Long competitionId) {
}
