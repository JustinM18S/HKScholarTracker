package com.example.hkscholarhub;

public class LoginResponse {
    private boolean status;
    private String message;
    private String userType;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUserType() {
        return userType;
    }
}