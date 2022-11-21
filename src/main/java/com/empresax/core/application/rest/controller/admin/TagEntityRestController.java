package com.empresax.core.application.rest.controller.admin;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.domain.api.TagEntityApi;
import com.empresax.core.infrastructure.entity.TagEntity;
import com.empresax.core.infrastructure.repository.ITagEntityCrudRepository;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;

@Hidden
@RestController
@RequestMapping(value = "/api/v1/admin/tags")
@AllArgsConstructor
public class TagEntityRestController implements TagEntityApi {

    private ITagEntityCrudRepository tagEntityCrudRepository;

    @Override
    public ResponseEntity<TagEntity> saveTagEntity(@Valid @RequestBody TagEntity tagEntity) {
        return new ResponseEntity<>(tagEntityCrudRepository.save(tagEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<TagEntity>> findAllTagsEntity() {
        return new ResponseEntity<>(tagEntityCrudRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TagEntity> findTagEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(tagEntityCrudRepository.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TagEntity> updateTagEntityById(@Valid @RequestBody TagEntity entity) {
        return new ResponseEntity<>(tagEntityCrudRepository.save(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTagEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
