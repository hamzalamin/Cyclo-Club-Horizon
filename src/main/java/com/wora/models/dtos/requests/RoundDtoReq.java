package com.wora.models.dtos.requests;

import com.wora.models.entities.Competition;
import com.wora.models.entities.RoundResult;

import java.time.LocalDate;
import java.util.List;

public record RoundDtoReq(
        Integer stageNumber,
        LocalDate startDte,
        LocalDate endDte
) {
}
