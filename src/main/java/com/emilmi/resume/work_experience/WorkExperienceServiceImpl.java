package com.emilmi.resume.work_experience;

import com.emilmi.resume.common.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final WorkExperienceMapper workExperienceMapper;
    private final Common<WorkExperience> _common;

    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository, WorkExperienceMapper workExperienceMapper, Common<WorkExperience> common) {
        this.workExperienceRepository = workExperienceRepository;
        this.workExperienceMapper = workExperienceMapper;
        _common = common;
    }


    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, _common.parseSort(sort));
        Page<WorkExperience> pageWorkExperience = workExperienceRepository.findAll(paging);

        List<WorkExperienceDto> workExperienceResponseList = pageWorkExperience.getContent()
                .stream()
                .map(workExperienceMapper::toDto)
                .toList();

        return _common.generatePageResponse(
                size,
                pageWorkExperience,
                workExperienceResponseList,
                WorkExperience.class
        );
    }

    @Override
    public WorkExperienceDto findById(String id) {
        return workExperienceMapper.toDto(
                this._common.getById(workExperienceRepository, id)
        );
    }

    @Override
    public WorkExperienceDto add(WorkExperienceDto workExperienceDto) {
        return workExperienceMapper.toDto(
                workExperienceRepository.save(
                        workExperienceMapper.toDocument(workExperienceDto)
                )
        );
    }

    @Override
    public WorkExperienceDto update(WorkExperienceDto workExperienceDto, String id) {
        this._common.getById(workExperienceRepository, id);

        return workExperienceMapper.toDto(
                workExperienceRepository.save(
                        workExperienceMapper.toDocument(workExperienceDto, id)
                )
        );
    }

    @Override
    public void delete(String id) {
        workExperienceRepository.deleteById(id);
    }
}
