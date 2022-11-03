package com.module.core.exception;

import org.springframework.core.NestedRuntimeException;

public class CommonException extends NestedRuntimeException {

    private ErrorType type = ErrorType.ERROR;
    private ErrorStatusCode statusCode = ErrorStatusCode.ERROR_SYSTEM_EXCEPTION;
    private String message;

    public CommonException(){
        super("");
        this.message = "";
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(ErrorType type, String message) {
        super(message);
        this.type = type;
        this.message = message;
    }

    public CommonException(ErrorType type, ErrorStatusCode statusCode, String message) {
        super(message);
        this.type = type;
        this.statusCode = statusCode;
        this.message = message;
    }

}
