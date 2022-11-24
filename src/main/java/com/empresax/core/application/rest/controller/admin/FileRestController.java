package com.empresax.core.application.rest.controller.admin;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.empresax.core.domain.api.FileApi;
import com.empresax.core.domain.model.dto.FileResponse;
import com.empresax.core.domain.service.IFileService;

@RestController
@RequestMapping(value = "/api/v1/admin/media")
public class FileRestController implements FileApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileRestController.class);
    @Autowired
    private IFileService fileService;
    @Value(value = "${project.media}")
    private String path;

    @Override
    public ResponseEntity<FileResponse> fileUploadImg(@RequestParam MultipartFile image) {
        String fileName = null;
        try {
            fileName = fileService.uploadImg(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new FileResponse(fileName, "An error has occurred while uploading the image."),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                new FileResponse(fileName, "Image successfully uploaded"),
                HttpStatus.OK);
    }

    @Override
    public void downloadImg(@PathVariable String img, HttpServletResponse response) {
        try {
            InputStream resource = fileService.getImg(path, img);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(resource, response.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("Image not foudn", e);
        }

    }
}