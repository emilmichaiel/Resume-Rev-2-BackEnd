package com.emilmi.resume;

import com.emilmi.resume.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableConfigurationProperties(StorageProperties.class)
public class EmilmiResumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmilmiResumeApplication.class, args);
    }

}
