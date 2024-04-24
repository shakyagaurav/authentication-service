package com.demo.exception;

import java.io.Serial;

public class UsernameIsNotValidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UsernameIsNotValidException() {
        super();
    }

    public UsernameIsNotValidException(String message) {
        super(message);
    }

    public UsernameIsNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
