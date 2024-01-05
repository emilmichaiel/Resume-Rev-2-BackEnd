package com.emilmi.resume.certificate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CertificateMapper {
    CertificateDto toDto(Certificate certificate);

    Certificate toDocument(CertificateDto certificateDto);

    @Mapping(target = "id", source = "id")
    Certificate toDocument(CertificateDto certificateDto, String id);
}
