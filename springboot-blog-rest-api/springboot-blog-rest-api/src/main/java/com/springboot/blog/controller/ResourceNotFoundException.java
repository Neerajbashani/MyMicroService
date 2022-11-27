package com.springboot.blog.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String reesourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String message, String reesourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s:'%s'", reesourceName, fieldName, fieldValue));
        this.reesourceName = reesourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException() {

    }
}
