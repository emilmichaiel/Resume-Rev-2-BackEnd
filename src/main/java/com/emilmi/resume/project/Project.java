package com.emilmi.resume.project;

import com.emilmi.resume.category.Category;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "projects")
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    private String url;
    private String imageUrl;
    @DBRef
    private List<Category> categories;
    @CreatedDate
    private LocalDateTime createdDateTime;
    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    public Project() {
    }

    public Project(String id, String name, String description, String url, String imageUrl, List<Category> categories, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.categories = categories;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }
}
