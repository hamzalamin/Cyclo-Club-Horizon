package com.wora.models.dtos.responses;

import com.wora.models.entities.Competition;
import com.wora.models.entities.RoundResult;

import java.time.LocalDate;
import java.util.List;

public record RoundDtoRes(
        Integer stageNumber,
        LocalDate startDte,
        LocalDate endDte,
        Competition competition,
        List<RoundResult> roundResults
) {
}
