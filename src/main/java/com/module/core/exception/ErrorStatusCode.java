package com.module.core.exception;

public enum ErrorStatusCode {

    // 레거시 코드에 사용되었던 기본적인 상태 코드들
    SUCCESS(200, "[SUCCESS]"),
    FAILED(300, "[FAILED]"),
    ERROR_SYSTEM_EXCEPTION(999, "[ERROR_SYSTEM_EXCEPTION]")
    ;
    private int error;
    private String reason;

    ErrorStatusCode(int error, String reason) {
        this.error = error;
        this.reason = reason;
    }

    public int getError() {
        return error;
    }

    public String getReason() {
        return reason;
    }

}
