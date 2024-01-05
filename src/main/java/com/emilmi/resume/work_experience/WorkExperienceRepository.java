package com.emilmi.resume.work_experience;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkExperienceRepository extends MongoRepository<WorkExperience, String> {
}
