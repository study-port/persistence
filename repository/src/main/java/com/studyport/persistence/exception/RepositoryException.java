package com.studyport.persistence.exception;

import lombok.Data;

@Data
public class RepositoryException extends Exception {

    private String errorMessage;
    private String errorCode;

    public RepositoryException(String message, String errorCode, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public RepositoryException(Throwable cause, String errorCode, String errorMessage) {
        super(cause.getMessage(), cause);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
