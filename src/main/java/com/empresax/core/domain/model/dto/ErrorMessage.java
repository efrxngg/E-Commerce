package com.empresax.core.domain.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private Date date;
    private String msg;
    private String path;

}
