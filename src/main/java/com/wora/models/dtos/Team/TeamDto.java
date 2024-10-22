package com.wora.models.dtos.Team;

import com.wora.models.entities.Rider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class TeamDto {
    @Positive
    private final Long id;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    private final List<Rider> riders;

    public TeamDto(@Positive Long id, @NotBlank String name, @NotBlank String description, List<Rider> riders) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.riders = riders;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Rider> getRiders() {
        return riders;
    }
}
