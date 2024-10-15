package com.wora.models.dtos;

import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.Round;

import java.time.LocalDate;
import java.util.List;

public record CompetitionDto(
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String location,
        List<GeneralResult> generalResults,
        List<Round> rounds
) {
}
