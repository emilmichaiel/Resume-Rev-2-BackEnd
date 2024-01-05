package com.emilmi.resume.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface StorageService {

    void init();

    FileDto store(MultipartFile file);

    List<FileDto> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void delete(String filename);
}

