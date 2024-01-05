package com.emilmi.resume.storage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StorageInit implements CommandLineRunner {

    private final StorageService storageService;

    public StorageInit(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void run(String... args) {
        storageService.init();
    }
}
