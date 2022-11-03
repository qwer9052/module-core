package com.module.core.exception;

public enum ErrorType {

    ERROR(0, "error");

    private int type;
    private String name;

    ErrorType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

}
