package com.wora.models.dtos.requests;

import java.time.LocalDate;

public record RoundDtoReq(
        Integer stageNumber,
        LocalDate startDte,
        LocalDate endDte
) {
}
