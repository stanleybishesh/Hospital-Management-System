package com.example.hms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecordConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecordConflictException() {
        super();
    }

    public RecordConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordConflictException(String message) {
        super(message);
    }

    public RecordConflictException(Throwable cause) {
        super(cause);
    }


}
