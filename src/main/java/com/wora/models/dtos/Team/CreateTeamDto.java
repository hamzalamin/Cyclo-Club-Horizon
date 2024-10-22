package com.wora.models.dtos.Team;

import jakarta.validation.constraints.NotBlank;

public class CreateTeamDto {
    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    public CreateTeamDto(@NotBlank String name, @NotBlank String description) {
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
