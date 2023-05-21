package com.task.lab.crypto_watcher.core.exception;


import com.task.lab.crypto_watcher.core.dto.error.ErrorCode;

public class ConversionTimeException extends RuntimeException {
    private ErrorCode errorCode;

    public ConversionTimeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ConversionTimeException() {
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}

