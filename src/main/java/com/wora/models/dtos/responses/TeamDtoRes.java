package com.wora.models.dtos.responses;

import com.wora.models.entities.Rider;

import java.util.List;

public record TeamDtoRes(
        String name,
        String description,
        List<Rider> riders
) {
}
