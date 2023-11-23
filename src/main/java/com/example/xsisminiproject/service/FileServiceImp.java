package com.example.xsisminiproject.service;

import com.example.xsisminiproject.config.StorageConfig;
import com.example.xsisminiproject.exception.ApiResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileServiceImp implements FileService {
    private final Path rootLocation;

    @Autowired
    public FileServiceImp(StorageConfig storageConfig) {
        if (storageConfig.getDir().trim().length() == 0) {
            throw new ApiResponseException("File upload location cannot be empty");
        }
        this.rootLocation = Paths.get(storageConfig.getDir());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new ApiResponseException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiResponseException("Failed to store empty file");
        }
        Path destFile = this.rootLocation.resolve(
                Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

        if(!destFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
            throw new ApiResponseException("Cannot store file outside current directory");
        }
        try(InputStream is = file.getInputStream()) {
            Files.copy(is, destFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ApiResponseException("Failed to store file.", e);
        }

    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new ApiResponseException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new ApiResponseException(
                        "Could not read file: " + fileName);

            }
        }
        catch (MalformedURLException e) {
            throw new ApiResponseException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

}
