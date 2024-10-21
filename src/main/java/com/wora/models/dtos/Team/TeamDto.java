package com.wora.models.dtos.Team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TeamDto(
        @Positive
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
