package com.example.introductory.dto.response;

import java.util.UUID;

public class AuthorizationResponseDto {
    private UUID uuid;

    private String token;

    private String refreshToken;

    /*      CONSTRUCTORS     */
    public AuthorizationResponseDto() {
    }

    public AuthorizationResponseDto(UUID uuid, String token, String refreshToken) {
        this.uuid = uuid;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    /*      GETTERS     */
    public UUID getUUID() {
        return uuid;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    /*      SETTERS     */
    public void setUsername(UUID uuid) {
        this.uuid = uuid;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}

