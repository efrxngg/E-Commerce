package com.empresax.core.domain.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.empresax.core.domain.model.dto.FileResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Media", description = "Only For Admins | Option enabled for testing")
@SecurityRequirement(name = "Bearer Authentication")
public interface FileApi {

    @Operation(summary = "Upload your images to the server")
    @PostMapping(value = "/imgs", consumes = { "multipart/form-data" })
    ResponseEntity<FileResponse> fileUploadImg(@RequestParam MultipartFile image);

    @Operation(summary = "Get the images of the server")
    @GetMapping(value = "/imgs/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
    void downloadImg(@PathVariable String img, HttpServletResponse response);

}