package com.userServiceAuth.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";
    private static final long EXPIRATION_TIME= 1000 * 60 * 60 * 10;
    private Key getSigningKey(){
         return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate JWT Token
    public String generateToken(String username){
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }
    // Extract username from token
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T>claimsResolver){
    Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }
    public boolean validateToken(String token, String username){
            return (username.equals(extractUserName(token)) && !isTokenExpired(token));
    }
    // Extract expiration date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
}
