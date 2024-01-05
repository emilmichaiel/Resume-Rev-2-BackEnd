package com.emilmi.resume.project;

import com.emilmi.resume.common.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final Common<Project> _common;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, Common<Project> common) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        _common = common;
    }

    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, _common.parseSort(sort));
        Page<Project> pageProjects = projectRepository.findAll(paging);

        List<ProjectDto> projectResponseList = pageProjects.getContent()
                .stream()
                .map(projectMapper::toDto)
                .toList();

        return _common.generatePageResponse(
                size,
                pageProjects,
                projectResponseList,
                Project.class
        );
    }

    @Override
    public ProjectDto findById(String id) {
        return projectMapper.toDto(
                this._common.getById(projectRepository, id)
        );
    }

    @Override
    public ProjectDto add(ProjectDto projectDto) {
        return projectMapper.toDto(
                projectRepository.save(
                        projectMapper.toDocument(projectDto)
                )
        );
    }

    @Override
    public ProjectDto update(ProjectDto projectDto, String id) {
        Project project = this._common.getById(projectRepository, id);

        return projectMapper.toDto(
                projectRepository.save(
                        projectMapper.toDocument(projectDto, project)
                )
        );
    }

    @Override
    public void delete(String id) {
        projectRepository.deleteById(id);
    }
}
