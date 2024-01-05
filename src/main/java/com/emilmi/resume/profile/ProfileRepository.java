package com.emilmi.resume.profile;

import org.springframework.data.mongodb.repository.MongoRepository;

interface ProfileRepository extends MongoRepository<Profile, String> {
}
