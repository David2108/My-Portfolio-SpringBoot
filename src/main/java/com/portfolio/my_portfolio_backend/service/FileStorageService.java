package com.portfolio.my_portfolio_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            throw new IOException("El archivo está vacío");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if(originalFilename!=null){
            int dotIndex = originalFilename.lastIndexOf(".");
            if(dotIndex > 0){
                extension = originalFilename.substring(dotIndex);
            }
        }

        String fileName = UUID.randomUUID() + extension;

        // normalize protege la ruta es decir mo permite que la ruta no sea otra que la definida
        Path filePath = Paths.get(uploadDir, fileName).normalize();

        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

}
