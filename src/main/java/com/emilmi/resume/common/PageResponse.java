package com.emilmi.resume.common;

public record PageResponse(
        Integer size,
        Long totalElements,
        Integer totalPages,
        Long number) {
}
