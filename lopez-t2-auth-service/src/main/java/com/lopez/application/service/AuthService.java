package com.lopez.application.service;


import com.lopez.application.dto.GenerateTokenReponse;
import com.lopez.application.dto.GenerateTokenRequest;


public interface AuthService {
    GenerateTokenReponse generateToken(GenerateTokenRequest generateTokenRequest);
}
