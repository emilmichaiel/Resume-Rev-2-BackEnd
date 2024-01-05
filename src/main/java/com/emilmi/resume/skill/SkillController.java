package com.emilmi.resume.skill;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id.ASC") String sort
    ) {
        return skillService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SkillDto findSkillById(
            @PathVariable String id
    ) {
        return skillService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillDto addSkill(
            @Valid @RequestBody SkillDto skillDto
    ) {
        return skillService.add(skillDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SkillDto updateSkill(
            @PathVariable String id,
            @Valid @RequestBody SkillDto skillDto
    ) {
        return skillService.update(skillDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkill(
            @PathVariable String id
    ) {
        skillService.delete(id);
    }
}
