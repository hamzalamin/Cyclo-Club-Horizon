package com.wora.models.dtos.roundResult;
import jakarta.validation.constraints.Positive;

public record CreateRoundResultDto(
        @Positive Long roundId,
        @Positive Long riderId
) {
}
