package com.thinkinnovative.library_management_system.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    // Secure key of at least 256 bits
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("this_is_a_very_secure_and_long_secret_key_123456".getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build();
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported token: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Malformed token: " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Invalid signature: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return false;
    }

    private boolean isTokenExpired(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build();
        return jwtParser.parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
