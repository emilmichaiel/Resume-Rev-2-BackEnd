package com.emilmi.resume.skill_group;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skill_groups")
public class SkillGroup {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;

    public SkillGroup() {
    }

    public SkillGroup(String id, String name) {
        this.id = id;
        this.name = name;
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
}
