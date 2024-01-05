package com.emilmi.resume.skill_group;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/skill-groups")
public class SkillGroupController {

    private final SkillGroupService skillGroupService;

    public SkillGroupController(SkillGroupService skillGroupService) {
        this.skillGroupService = skillGroupService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id.ASC") String sort,
            @RequestParam(defaultValue = "false") Boolean includeSkills
    ) {
        return skillGroupService.findAll(page, size, sort, includeSkills);
    }

    @GetMapping("/{id}/skills")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAllSkillsInSkillGroup(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id.ASC") String sort
    ) {
        return skillGroupService.findAll(page, size, sort, id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SkillGroupDto findSkillGroupById(
            @PathVariable String id
    ) {
        return skillGroupService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillGroupDto addSkillGroup(
            @Valid @RequestBody SkillGroupDto skillGroupRecord
    ) {
        return skillGroupService.add(skillGroupRecord);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SkillGroupDto updateSkillGroup(
            @PathVariable String id,
            @Valid @RequestBody SkillGroupDto skillGroupRecord
    ) {
        return skillGroupService.update(skillGroupRecord, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkillGroup(
            @PathVariable String id
    ) {
        skillGroupService.delete(id);
    }
}
