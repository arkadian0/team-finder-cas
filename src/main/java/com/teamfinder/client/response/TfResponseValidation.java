package com.teamfinder.client.response;

public class TfResponseValidation {
    private String code;
    private String message;

    public TfResponseValidation() {
    }

    public TfResponseValidation(String code) {
        this.code = code;
    }

    public TfResponseValidation(String code, String message) {
        this(code);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}