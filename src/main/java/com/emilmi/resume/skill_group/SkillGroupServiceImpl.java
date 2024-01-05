package com.emilmi.resume.skill_group;

import com.emilmi.resume.common.Common;
import com.emilmi.resume.skill.Skill;
import com.emilmi.resume.skill.SkillDto;
import com.emilmi.resume.skill.SkillMapper;
import com.emilmi.resume.skill.SkillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SkillGroupServiceImpl implements SkillGroupService {
    private final SkillGroupRepository skillGroupRepository;
    private final SkillGroupMapper skillGroupMapper;
    private final Common<SkillGroup> _common;
    private final Common<Skill> _commonSkill;
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillGroupServiceImpl(SkillGroupRepository skillGroupRepository, SkillGroupMapper skillGroupMapper, Common<SkillGroup> _common, Common<Skill> _commonSkill, SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillGroupRepository = skillGroupRepository;
        this.skillGroupMapper = skillGroupMapper;
        this._common = _common;
        this._commonSkill = _commonSkill;
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort) {
        return findAll(page, size, sort, false);
    }

    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort, Boolean includeSkills) {
        Pageable paging = PageRequest.of(page, size, _common.parseSort(sort));
        Page<SkillGroup> pageSkillGroups = skillGroupRepository.findAll(paging);
        List<SkillGroupDto> skillGroupResponseList;

        if (includeSkills) {
            skillGroupResponseList = pageSkillGroups.getContent()
                    .stream()
                    .map(skillGroup -> {
                        List<SkillDto> skills = skillRepository.findBySkillGroup(skillGroup)
                                .stream()
                                .map(skillMapper::toDto)
                                .toList();
                        return skillGroupMapper.toDto(skillGroup, skills);
                    })
                    .toList();
        } else {
            skillGroupResponseList = pageSkillGroups.getContent()
                    .stream()
                    .map(skillGroupMapper::toDto)
                    .toList();
        }

        return _common.generatePageResponse(
                size,
                pageSkillGroups,
                skillGroupResponseList,
                SkillGroup.class
        );
    }

    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort, String id) {
        SkillGroup skillGroup = _common.getById(skillGroupRepository, id);

        Pageable paging = PageRequest.of(page, size, _common.parseSort(sort));
        Page<Skill> pageSkillsInGroup = skillRepository.findBySkillGroup(skillGroup, paging);

        List<SkillDto> skillsInGroupResponseList = pageSkillsInGroup.getContent()
                .stream()
                .map(skillMapper::toDtoNoSkillGroup)
                .toList();

        return _commonSkill.generatePageResponse(
                size,
                pageSkillsInGroup,
                skillsInGroupResponseList,
                Skill.class
        );
    }

    @Override
    public SkillGroupDto findById(String id) {
        return skillGroupMapper.toDto(
                this._common.getById(skillGroupRepository, id)
        );
    }

    @Override
    public SkillGroupDto add(SkillGroupDto skillGroupDto) {
        return skillGroupMapper.toDto(
                skillGroupRepository.save(
                        skillGroupMapper.toDocument(skillGroupDto)
                )
        );
    }

    @Override
    public SkillGroupDto update(SkillGroupDto skillGroupDto, String id) {
        this._common.getById(skillGroupRepository, id);

        return skillGroupMapper.toDto(
                skillGroupRepository.save(
                        skillGroupMapper.toDocument(skillGroupDto, id)
                )
        );
    }

    @Override
    public void delete(String id) {
        skillGroupRepository.deleteById(id);
    }
}
