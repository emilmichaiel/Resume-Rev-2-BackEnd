package com.emilmi.resume.certificate;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CertificateRepository extends MongoRepository<Certificate, String> {
}
