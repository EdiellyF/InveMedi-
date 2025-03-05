package com.br.InveMedi.inveMedi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    public String generateToken(String email) {
        SecretKey key = getKeyBySecret();
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    private SecretKey getKeyBySecret(){

        byte[] keyBytes = secret.getBytes();
        if(keyBytes.length < 32){
            throw new IllegalArgumentException("Secret key must be at least 32 bytes long (256 bits for HMAC-SHA-256).");
        }
        return Keys.hmacShaKeyFor(this.secret.getBytes());

    }


    public boolean isValidateToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if(Objects.nonNull(claims)){
            String email = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if(Objects.nonNull(email) && Objects.nonNull(expiration) && now.before(expiration)){
                return true;
            }
        }
        return false;
    }


    private Claims getClaimsFromToken(String token) {
        SecretKey key = getKeyBySecret();
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
