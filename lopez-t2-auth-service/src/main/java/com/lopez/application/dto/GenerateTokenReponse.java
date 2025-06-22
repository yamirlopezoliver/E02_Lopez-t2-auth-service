package com.lopez.application.dto;

public record GenerateTokenReponse(String token, String tokenType, long expiresInSeconds) {
}
