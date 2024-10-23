package com.wora.models.dtos.rider;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record UpdateRiderDto(
        @NotBlank String fName,
        @NotBlank String lName,
        @NotNull LocalDate birthDate,
        @NotBlank String nationality,
        @Positive Long teamId
) {
}
