package com.wora.models.dtos.requests;
import java.time.LocalDate;


public record RiderDtoReq(
        String fName,
        String lName,
        LocalDate birthDate,
        String nationality
) {
}
