package com.emilmi.resume.certificate;

import com.emilmi.resume.common.ContentModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "certificates")
public class Certificate extends ContentModel {
    @Id
    private String id;
    private String url;

    public Certificate() {
        super();
    }

    public Certificate(String name, String description, LocalDate startDate, LocalDate endDate, String id, String url) {
        super(name, description, startDate, endDate);
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
