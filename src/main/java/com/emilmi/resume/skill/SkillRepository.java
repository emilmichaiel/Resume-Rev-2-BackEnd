package com.emilmi.resume.skill;

import com.emilmi.resume.skill_group.SkillGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SkillRepository extends MongoRepository<Skill, String> {
    Page<Skill> findBySkillGroup(SkillGroup skillGroup, Pageable pageable);

    List<Skill> findBySkillGroup(SkillGroup skillGroup);
}
