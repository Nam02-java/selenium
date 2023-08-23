package com.bezkoder.springjwt.payload.request;

import jakarta.validation.constraints.NotBlank;

public class FunctionRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String mission;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }
}
