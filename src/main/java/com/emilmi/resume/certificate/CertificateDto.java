package com.emilmi.resume.certificate;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CertificateDto(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String id,
        @NotEmpty(message = "name cannot be empty")
        String name,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String url,
        @NotNull(message = "startDate cannot be empty")
        LocalDate startDate,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDate endDate
) {
}
