package com.emilmi.resume.education;

import com.emilmi.resume.common.ContentModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "educations")
public class Education extends ContentModel {
    @Id
    private String id;

    public Education() {
        super();
    }

    public Education(String name, String description, LocalDate startDate, LocalDate endDate, String id) {
        super(name, description, startDate, endDate);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
