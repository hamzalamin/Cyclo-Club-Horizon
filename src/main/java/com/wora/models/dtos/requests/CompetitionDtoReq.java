package com.wora.models.dtos.requests;

import java.time.LocalDate;

public record CompetitionDtoReq(
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String location
) {
}
