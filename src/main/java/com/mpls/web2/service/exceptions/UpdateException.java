package com.mpls.web2.service.exceptions;

public class UpdateException extends RuntimeException {
    private static String prefix = "UpdateException:[ ";
    private String message;

    public UpdateException(String message) {
        this.message = prefix + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
    public static UpdateException  build(String message){
        return new UpdateException(message);
    }
}
