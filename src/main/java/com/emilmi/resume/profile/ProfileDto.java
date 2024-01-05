package com.emilmi.resume.profile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public record ProfileDto(
        String firstName,
        String lastName,
        String email,
        String country,
        String city,
        String degree,
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate dateOfBirth,
        String bio,
        String facebook,
        String github,
        String linkedin,
        String profilePicUrl
) {
}
