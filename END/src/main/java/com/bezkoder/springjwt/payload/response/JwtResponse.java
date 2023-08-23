package com.bezkoder.springjwt.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";

    /**
     * new update about refreshToken at 9:22AM - 5/9
     */
    private String refreshToken;

    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    private String function_name;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    /**
     * new update about refreshToken at 9:24AM - 5/9
     */
    public JwtResponse(String token, String refreshToken, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String token, String refreshToken, Long id, String username, String email, List<String> roles,String function_name) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.function_name = function_name;
    }

    public JwtResponse(String token, String refreshToken, Long id, String username, String email,String function_name, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.function_name = function_name;
        this.roles = roles;
    }





    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    /**
     * new update about refreshToken at 9:24AM - 5/9
     */
    public String getRefreshToken() {
        return refreshToken;
    }
    /**
     * new update about refreshToken at 9:24AM - 5/9
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
