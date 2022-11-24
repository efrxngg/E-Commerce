package com.empresax.core.domain.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.empresax.core.domain.service.IFileService;

@Service
public class FileServiceImpl implements IFileService {

    @Override
    public String uploadImg(String path, MultipartFile file) throws IOException {
        // File Name
        String name = file.getOriginalFilename() + "";
        // Random name generator
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));
        // File Full Path
        String filePath = path + File.separator + fileName;
        try {
            // File create if not exists
            File newFile = new File(path);
            if (!newFile.exists())
                newFile.mkdir();
            // Copy
            Files.copy(file.getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error al momento de crear o manipular el archivo de img");
        }
        return fileName;
    }

    @Override
    public InputStream getImg(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        try {
            InputStream is = new FileInputStream(fullPath);
            return is;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Error al momento de despachar la img");
        }
    }

}