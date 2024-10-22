package com.wora.models.dtos.Team;

import com.wora.models.entities.Rider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record TeamDto(
        @Positive Long id,
        @NotBlank String name,
        @NotBlank String description,
        List<Rider> riders

) {
}
