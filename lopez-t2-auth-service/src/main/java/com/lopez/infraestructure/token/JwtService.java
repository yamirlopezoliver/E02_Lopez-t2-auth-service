package com.lopez.infraestructure.token;

import com.lopez.domain.model.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${security.jwt.secret}")
    private String secretKey;
    @Value("${security.jwt.expiration}")
    private long expirationMillis;
    private Key signingKey;
    @PostConstruct
    public void init(){
        signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public String generateToken(Client client){
        return Jwts.builder()
                .setSubject(client.getClientId())
                .claim("clienteId", client.getClientId())
                .claim("description", client.getDescription())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

}
