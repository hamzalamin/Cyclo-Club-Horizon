package com.wora.models.dtos;

import java.time.LocalDate;

public record CompetitionDto(
        LocalDate endDate,
        String location,
        String name,
        LocalDate startDate
) {
}
