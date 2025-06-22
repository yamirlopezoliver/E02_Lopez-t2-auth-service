package com.lopez.application.service.impl;

import com.lopez.application.dto.GenerateTokenReponse;
import com.lopez.application.dto.GenerateTokenRequest;
import com.lopez.application.service.AuthService;
import com.lopez.domain.repository.ClientRepository;
import com.lopez.infraestructure.token.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ClientRepository clientRepository;
    private final JwtService jwtService;
    @Override
    public GenerateTokenReponse generateToken(GenerateTokenRequest generateTokenRequest) {
        var client = clientRepository.findByClientId(generateTokenRequest.clientId())
                .orElseThrow(()-> new RuntimeException("Client ID inválido"));
        if (!client.isEnabled() || !client.getClientSecret().equals(generateTokenRequest.clientSecret())){
            throw new RuntimeException("Crendeciales inválidas");
        }
        String token = jwtService.generateToken(client);
        Date expiration = jwtService.extractExpiration(token);
        long secondsToExpire = (expiration.getTime() - System.currentTimeMillis())/1000;
        return new GenerateTokenReponse(token, "Bearer", secondsToExpire);
    }
}
