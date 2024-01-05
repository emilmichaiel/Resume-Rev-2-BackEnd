package com.emilmi.resume.work_experience;

import com.emilmi.resume.common.ContentModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "work_experiences")
public class WorkExperience extends ContentModel {
    @Id
    private String id;
    private List<Project> projects;

    public WorkExperience() {
        super();
    }

    public WorkExperience(String name, String description, LocalDate startDate, LocalDate endDate, String id, List<Project> projects) {
        super(name, description, startDate, endDate);
        this.id = id;
        this.projects = projects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
