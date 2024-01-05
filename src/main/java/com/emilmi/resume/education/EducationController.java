package com.emilmi.resume.education;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/educations")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id.ASC") String sort
    ) {
        return educationService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EducationDto findEducationById(
            @PathVariable String id
    ) {
        return educationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationDto addEducation(
            @Valid @RequestBody EducationDto educationDto
    ) {
        return educationService.add(educationDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EducationDto updateEducation(
            @PathVariable String id,
            @Valid @RequestBody EducationDto educationDto
    ) {
        return educationService.update(educationDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEducation(
            @PathVariable String id
    ) {
        educationService.delete(id);
    }
}
