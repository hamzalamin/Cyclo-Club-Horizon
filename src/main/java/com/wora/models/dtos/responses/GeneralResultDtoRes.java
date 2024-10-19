package com.wora.models.dtos.responses;

import com.wora.models.entities.Competition;
import com.wora.models.entities.Rider;
import com.wora.models.entities.embeddables.GeneralResultId;

import java.time.Duration;

public record GeneralResultDtoRes(
        GeneralResultId id,
        Duration generalTime,
        Integer range,
        Competition competition,
        Rider rider
) {
}
