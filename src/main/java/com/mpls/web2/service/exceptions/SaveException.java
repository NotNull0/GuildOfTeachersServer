package com.mpls.web2.service.exceptions;

public class SaveException extends RuntimeException {
    private static String prefix = "SaveException:[ ";
    private String message;

    public SaveException(String message) {
        this.message = prefix + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
