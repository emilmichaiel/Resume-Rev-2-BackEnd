package com.emilmi.resume.work_experience;

public class Project {
    private String description;
    private String name;
    private String url;

    public Project() {
    }

    public Project(String description, String name, String url) {
        this.description = description;
        this.name = name;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
