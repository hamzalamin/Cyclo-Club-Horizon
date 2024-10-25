package com.wora.models.dtos.generalResult;

import com.wora.models.dtos.embeded.GeneralResultIdDto;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record EmbeddedGeneralResultDto(
        GeneralResultIdDto id,
        @NotNull Duration duration,
        @NotNull Integer position
) {
}
