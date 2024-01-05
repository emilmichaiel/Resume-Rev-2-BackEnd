package com.emilmi.resume.exception;

import java.time.LocalDate;
import java.util.List;

public record ErrorResponse(
        List<String> errors,
        String uri,
        LocalDate timestamp,
        Integer status
) {
}
