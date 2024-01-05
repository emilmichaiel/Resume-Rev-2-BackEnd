package com.emilmi.resume.storage;

public record FileDto(
        String name,
        String url,
        String type,
        Long size
) {
}
