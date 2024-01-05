package com.emilmi.resume.project;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id.ASC") String sort
    ) {
        return projectService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto findProjectById(
            @PathVariable String id
    ) {
        return projectService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto addProject(
            @Valid @RequestBody ProjectDto projectDto
    ) {
        return projectService.add(projectDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProjectDto updateProject(
            @PathVariable String id,
            @Valid @RequestBody ProjectDto projectDto
    ) {
        return projectService.update(projectDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(
            @PathVariable String id
    ) {
        projectService.delete(id);
    }
}
