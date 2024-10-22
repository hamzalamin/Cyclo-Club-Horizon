package com.wora.models.dtos.Team;

import jakarta.validation.constraints.NotBlank;

public record UpdateTeamDto(
        @NotBlank String name,
        @NotBlank String description
) {
}
