package com.wora.models.dtos.responses;

import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.RoundResult;
import com.wora.models.entities.Team;

import java.time.LocalDate;
import java.util.List;

public record RiderDtoRes(
        String fName,
        String lName,
        LocalDate birthDate,
        String nationality,
        Team team,
        List<GeneralResult> generalResults,
        List<RoundResult> roundResults
) {
}
