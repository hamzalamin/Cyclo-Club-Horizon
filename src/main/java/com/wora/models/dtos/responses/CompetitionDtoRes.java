package com.wora.models.dtos.responses;

import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.Round;

import java.time.LocalDate;
import java.util.List;

public record CompetitionDtoRes(
        LocalDate endDate,
        String location,
        String name,
        LocalDate startDate,
        List<GeneralResult> generalResults,
        List<Round> rounds
) {
}
