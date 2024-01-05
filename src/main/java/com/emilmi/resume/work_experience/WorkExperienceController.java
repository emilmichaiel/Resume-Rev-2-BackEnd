package com.emilmi.resume.work_experience;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/work_experiences")
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    public WorkExperienceController(WorkExperienceService workExperienceService) {
        this.workExperienceService = workExperienceService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id.ASC") String sort
    ) {
        return workExperienceService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkExperienceDto findWorkExperienceById(
            @PathVariable String id
    ) {
        return workExperienceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkExperienceDto addWorkExperience(
            @Valid @RequestBody WorkExperienceDto workExperienceDto
    ) {
        return workExperienceService.add(workExperienceDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public WorkExperienceDto updateWorkExperience(
            @PathVariable String id,
            @Valid @RequestBody WorkExperienceDto workExperienceDto
    ) {
        return workExperienceService.update(workExperienceDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkExperience(
            @PathVariable String id
    ) {
        workExperienceService.delete(id);
    }
}
