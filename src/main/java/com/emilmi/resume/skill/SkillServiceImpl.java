package com.emilmi.resume.skill;

import com.emilmi.resume.common.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final Common<Skill> _common;

    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper, Common<Skill> common) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
        _common = common;
    }

    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, _common.parseSort(sort));
        Page<Skill> pageSkills = skillRepository.findAll(paging);

        List<SkillDto> skillResponseList = pageSkills.getContent()
                .stream()
                .map(skillMapper::toDto)
                .toList();

        return _common.generatePageResponse(
                size,
                pageSkills,
                skillResponseList,
                Skill.class
        );
    }

    @Override
    public SkillDto findById(String id) {
        return skillMapper.toDto(
                this._common.getById(skillRepository, id)
        );
    }

    @Override
    public SkillDto add(SkillDto skillDto) {
        return skillMapper.toDto(
                skillRepository.save(
                        skillMapper.toDocument(skillDto)
                )
        );
    }

    @Override
    public SkillDto update(SkillDto skillDto, String id) {
        this._common.getById(skillRepository, id);

        return skillMapper.toDto(
                skillRepository.save(
                        skillMapper.toDocument(skillDto, id)
                )
        );
    }

    @Override
    public void delete(String id) {
        skillRepository.deleteById(id);
    }
}
