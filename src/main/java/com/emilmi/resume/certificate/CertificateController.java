package com.emilmi.resume.certificate;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id.ASC") String sort
    ) {
        return certificateService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto findCertificateById(
            @PathVariable String id
    ) {
        return certificateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificateDto addCertificate(
            @Valid @RequestBody CertificateDto certificateDto
    ) {
        return certificateService.add(certificateDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CertificateDto updateCertificate(
            @PathVariable String id,
            @Valid @RequestBody CertificateDto certificateDto
    ) {
        return certificateService.update(certificateDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCertificate(
            @PathVariable String id
    ) {
        certificateService.delete(id);
    }
}
