package com.emilmi.resume.skill;

import com.emilmi.resume.exception.RestApiException;
import com.emilmi.resume.skill_group.SkillGroup;
import com.emilmi.resume.skill_group.SkillGroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SkillGroupSkillMapper {
    private final SkillGroupRepository skillGroupRepository;

    public SkillGroupSkillMapper(SkillGroupRepository skillGroupRepository) {
        this.skillGroupRepository = skillGroupRepository;
    }

    @SkillGroupLookup
    public SkillGroup map(String skillGroupId) {
        return skillGroupId == null ?
                null :
                skillGroupRepository.findById(skillGroupId)
                        .orElseThrow(() -> new RestApiException(STR. "skillGroupId '\{ skillGroupId }' does not exist" , HttpStatus.NOT_FOUND));
    }
}
