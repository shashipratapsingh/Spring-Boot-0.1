package com.jwt.entity;

public class JwtResponse {

    private User user;
    private String jwtToken;

    public JwtResponse(User user, String newGeneratedToken) {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
