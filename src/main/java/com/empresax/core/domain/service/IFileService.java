package com.empresax.core.domain.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String uploadImg(String path, MultipartFile file) throws IOException;

    InputStream getImg(String path, String fileName) throws FileNotFoundException;

}
