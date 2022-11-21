package com.empresax.core.domain.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.infrastructure.entity.TagEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tag Entity", description = "Only For Admins")
@SecurityRequirement(name = "Bearer Authentication")
public interface TagEntityApi {

    @Operation(summary = "Create a new Tag")
    @PostMapping
    ResponseEntity<TagEntity> saveTagEntity(TagEntity tagEntity);

    @Operation(summary = "Find all Tags")
    @GetMapping(value = "/all")
    ResponseEntity<List<TagEntity>> findAllTagsEntity();

    @Operation(summary = "Find by Id TagEntity all Tags")
    @GetMapping(value = "/{id}")
    ResponseEntity<TagEntity> findTagEntityById(UUID id);

    @Operation(summary = "Update TagEntity by ID")
    @PutMapping(value = "/{id}")
    ResponseEntity<TagEntity> updateTagEntityById(TagEntity entity);

    @Operation(summary = "Update TagEntity by ID")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteTagEntityById(UUID id);
}