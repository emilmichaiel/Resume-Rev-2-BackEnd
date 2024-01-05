package com.emilmi.resume.certificate;

import com.emilmi.resume.common.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;
    private final Common<Certificate> _common;

    public CertificateServiceImpl(CertificateRepository certificateRepository, CertificateMapper certificateMapper, Common<Certificate> common) {
        this.certificateRepository = certificateRepository;
        this.certificateMapper = certificateMapper;
        _common = common;
    }

    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, _common.parseSort(sort));
        Page<Certificate> pageCertificate = certificateRepository.findAll(paging);

        List<CertificateDto> certificateResponseList = pageCertificate.getContent()
                .stream()
                .map(certificateMapper::toDto)
                .toList();

        return _common.generatePageResponse(
                size,
                pageCertificate,
                certificateResponseList,
                Certificate.class
        );
    }

    @Override
    public CertificateDto findById(String id) {
        return certificateMapper.toDto(
                this._common.getById(certificateRepository, id)
        );
    }

    @Override
    public CertificateDto add(CertificateDto certificateDto) {
        return certificateMapper.toDto(
                certificateRepository.save(
                        certificateMapper.toDocument(certificateDto)
                )
        );
    }

    @Override
    public CertificateDto update(CertificateDto certificateDto, String id) {
        this._common.getById(certificateRepository, id);

        return certificateMapper.toDto(
                certificateRepository.save(
                        certificateMapper.toDocument(certificateDto, id)
                )
        );
    }

    @Override
    public void delete(String id) {
        certificateRepository.deleteById(id);
    }
}
