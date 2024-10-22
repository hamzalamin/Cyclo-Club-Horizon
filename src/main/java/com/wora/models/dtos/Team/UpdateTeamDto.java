package com.wora.models.dtos.Team;

import jakarta.validation.constraints.NotBlank;

public class UpdateTeamDto {
    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    public UpdateTeamDto(@NotBlank String name, @NotBlank String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
