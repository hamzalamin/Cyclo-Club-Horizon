package com.wora.models.dtos.rider;

import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.RoundResult;
import com.wora.models.entities.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record RiderDto(
        @Positive Long id,
        @NotBlank String fName,
        @NotBlank String lName,
        @NotNull LocalDate birthDate,
        @NotBlank String nationality,
        Team team,
        List<GeneralResult> generalResults,
        List<RoundResult> roundResults
) {
}
