package com.emilmi.resume.education;

import com.emilmi.resume.common.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;
    private final Common<Education> _common;

    public EducationServiceImpl(EducationRepository educationRepository, EducationMapper educationMapper, Common<Education> common) {
        this.educationRepository = educationRepository;
        this.educationMapper = educationMapper;
        _common = common;
    }


    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, _common.parseSort(sort));
        Page<Education> pageEducation = educationRepository.findAll(paging);

        List<EducationDto> educationResponseList = pageEducation.getContent()
                .stream()
                .map(educationMapper::toDto)
                .toList();

        return _common.generatePageResponse(
                size,
                pageEducation,
                educationResponseList,
                Education.class
        );
    }

    @Override
    public EducationDto findById(String id) {
        return educationMapper.toDto(
                this._common.getById(educationRepository, id)
        );
    }

    @Override
    public EducationDto add(EducationDto educationDto) {
        return educationMapper.toDto(
                educationRepository.save(
                        educationMapper.toDocument(educationDto)
                )
        );
    }

    @Override
    public EducationDto update(EducationDto educationDto, String id) {
        System.out.println(id);
        System.out.println(educationRepository.findById(id).get());
        this._common.getById(educationRepository, id);

        return educationMapper.toDto(
                educationRepository.save(
                        educationMapper.toDocument(educationDto, id)
                )
        );
    }

    @Override
    public void delete(String id) {
        educationRepository.deleteById(id);
    }
}
