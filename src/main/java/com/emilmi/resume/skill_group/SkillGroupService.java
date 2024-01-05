package com.emilmi.resume.skill_group;

import com.emilmi.resume.common.EmilMiGenericService;

import java.util.Map;

public interface SkillGroupService extends EmilMiGenericService<SkillGroupDto> {
    Map<String, Object> findAll(Integer page, Integer size, String sort, Boolean includeSkills);

    Map<String, Object> findAll(Integer page, Integer size, String sort, String id);
}
