package com.project.recipeweb.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {
    private final Path filesDir;
    private final ObjectMapper objectMapper;
    public FileService(ObjectMapper objectMapper, @Value("${app.files.dir}$") Path filesDir) {
        this.objectMapper=objectMapper;
        this.filesDir=filesDir;
    }

    public <T> void saveToFile(String fileName, T objectToSave){
        try{
            String json = objectMapper.writeValueAsString(objectToSave);
            Files.createDirectories(filesDir);
            Path filePath = filesDir.resolve(fileName + "./json");
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
            Files.writeString(filePath, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T readFromFile(String fileName, TypeReference <T> typeReference) {
        Path filePath = filesDir.resolve(fileName + ".json");
        if (!Files.exists(filePath)){
            return null;
        }
        try {
            String jsonString = Files.readString(filePath);
            T obj = objectMapper.readValue(jsonString, typeReference);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Resource getResource (String filename) {
        Path filePath = filesDir.resolve(filename + ".json");
        return new FileSystemResource(filePath);
    }

    public void saveResource (String filename, Resource resource) {
        Path filePath = filesDir.resolve(filename + ".json");

        try {
            Files.copy(resource.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
