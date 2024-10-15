package com.wora.models.dtos;

import com.wora.models.entities.Rider;
import com.wora.models.entities.embeddables.RoundResultId;

import java.util.List;

public record TeamDto(
        String name,
        String description,
        List<Rider> riders
) {
}
