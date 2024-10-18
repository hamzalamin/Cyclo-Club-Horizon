package com.wora.models.dtos.responses;

import com.wora.models.entities.Rider;
import com.wora.models.entities.Team;

import java.util.List;

public record TeamDtoRes(
        String name,
        String description,
        Team team,
        List<Rider> riders
) {
}
