package com.emilmi.resume.education;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EducationRepository extends MongoRepository<Education, String> {
}
