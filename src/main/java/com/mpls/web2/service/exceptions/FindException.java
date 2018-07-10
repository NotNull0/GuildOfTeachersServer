package com.mpls.web2.service.exceptions;

public class FindException extends RuntimeException {
    private static String prefix = "FindException:[ ";
    private String message;

    public FindException(String message) {
        this.message = prefix + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
