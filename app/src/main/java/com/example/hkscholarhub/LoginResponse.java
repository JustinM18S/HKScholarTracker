package com.example.hkscholarhub;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("role")
    private String role;

    // Getters
    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }
}