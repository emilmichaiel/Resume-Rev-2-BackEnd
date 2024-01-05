package com.emilmi.resume.storage;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/files")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<FileDto> listUploadedFiles() {
        return storageService.loadAll();
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                STR. "attachment; filename=\"\{ file.getFilename() }\"" ).body(file);
    }

    @PostMapping("")
    public FileDto handleFileUpload(@RequestParam("file") MultipartFile file) {
        return storageService.store(file);
    }

    @DeleteMapping("/{filename:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable String filename) {
        storageService.delete(filename);
    }
}
