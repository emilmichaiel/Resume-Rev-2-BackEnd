package com.emilmi.resume.storage;

import com.emilmi.resume.exception.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RestApiException("Could not initialize storage", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FileDto store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RestApiException("Failed to store empty file.", HttpStatus.BAD_REQUEST);
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new RestApiException("Cannot store file outside current directory.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            Path path = Paths.get(STR. "\{ this.rootLocation }/\{ file.getOriginalFilename() }" );
            return new FileDto(
                    file.getOriginalFilename(),
                    MvcUriComponentsBuilder
                            .fromMethodName(StorageController.class, "serveFile", file.getOriginalFilename())
                            .build().toUri().toString(),
                    Files.probeContentType(path),
                    Files.size(path)
            );
        } catch (IOException e) {
            throw new RestApiException("Failed to store file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<FileDto> loadAll() {
        try {
            List<FileDto> fileList;

            try (Stream<Path> fileStream = Files.list(this.rootLocation)) {
                fileList = fileStream
                        .filter(Files::isRegularFile)
                        .map(path -> {
                            String filename = path.getFileName().toString();
                            String fileUrl = MvcUriComponentsBuilder
                                    .fromMethodName(StorageController.class, "serveFile", filename)
                                    .build().toUri().toString();
                            long fileSize;
                            String fileType;
                            try {
                                fileSize = Files.size(path);
                                fileType = Files.probeContentType(path);
                            } catch (IOException e) {
                                throw new RestApiException("Failed to read stored files", HttpStatus.INTERNAL_SERVER_ERROR);
                            }

                            return new FileDto(
                                    filename,
                                    fileUrl,
                                    fileType,
                                    fileSize
                            );
                        })
                        .collect(Collectors.toList());
            }

            return fileList;
        } catch (IOException e) {
            throw new RestApiException("Failed to read stored files", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RestApiException(STR. "Could not read file: \{ filename }" , HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (MalformedURLException e) {
            throw new RestApiException(STR. "Could not read file: \{ filename }" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            FileSystemUtils.deleteRecursively(load(filename));
        } catch (IOException e) {
            throw new RestApiException(STR. "Could not delete file: \{ filename }" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
