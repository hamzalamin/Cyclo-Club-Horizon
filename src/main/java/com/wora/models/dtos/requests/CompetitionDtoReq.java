package com.wora.models.dtos.requests;

import java.time.LocalDate;

public record CompetitionDtoReq(
        LocalDate endDate,
        String location,
        String name,
        LocalDate startDate
) {
}
