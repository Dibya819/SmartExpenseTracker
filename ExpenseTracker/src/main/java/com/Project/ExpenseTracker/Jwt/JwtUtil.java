package com.Project.ExpenseTracker.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET="mysecretkeymysecretkeymysecretkey";
    private final long EXPIRATION_MS=1000*60*60*24;
    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        try{
            String username=extractUsername(token);
            return username.equals(userDetails.getUsername()) && ! isTokenExpired(token);
        }catch (Exception e){
            return false;
        }
    }
    private boolean isTokenExpired(String token){
        Date expiration=Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build().parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
