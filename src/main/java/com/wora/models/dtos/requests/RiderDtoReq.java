package com.wora.models.dtos.responses;
import java.time.LocalDate;


public record RiderDtoReq(
        String fName,
        String lName,
        LocalDate birthDate,
        String nationality
//        Team team
) {
}
