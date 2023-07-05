package com.reto.cliente.exceptions;

public class UploadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final String DESCRIPTION = "Upload Exception (404)";

    public UploadException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}