package com.emilmi.resume.skill;

import com.emilmi.resume.skill_group.SkillGroup;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skills")
public class Skill {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Double level;
    private String extraNotes;
    @DBRef
    private SkillGroup skillGroup;

    public Skill() {
    }

    public Skill(String id, String name, Double level, String extraNotes, SkillGroup skillGroup) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.extraNotes = extraNotes;
        this.skillGroup = skillGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public String getExtraNotes() {
        return extraNotes;
    }

    public void setExtraNotes(String extraNotes) {
        this.extraNotes = extraNotes;
    }

    public SkillGroup getSkillGroup() {
        return skillGroup;
    }

    public void setSkillGroup(SkillGroup skillGroup) {
        this.skillGroup = skillGroup;
    }
}
