package com.lopez.web.controller;

import com.lopez.application.dto.GenerateTokenReponse;
import com.lopez.application.dto.GenerateTokenRequest;
import com.lopez.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/token")
    public ResponseEntity<GenerateTokenReponse> generateToken(@RequestBody GenerateTokenRequest request){
        GenerateTokenReponse reponse = authService.generateToken(request);
        return ResponseEntity.ok(reponse);
    }
}
