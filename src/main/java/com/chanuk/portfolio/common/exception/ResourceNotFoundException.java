package com.chanuk.portfolio.common.exception;
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " not found: " + id);
    }
}
