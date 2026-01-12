package com.project.LibraryManagementSystem.exceptions;

import lombok.Data;

@Data
public class ErrorDetails {
    private int status;
    private String error;
    private String message;

    public ErrorDetails(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
