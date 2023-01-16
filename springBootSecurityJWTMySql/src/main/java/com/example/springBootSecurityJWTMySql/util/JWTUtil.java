package com.example.springBootSecurityJWTMySql.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {

    @Value("{$app.secret}")
    private String secret;

    /***1.Generate the token***/
    public String generateToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("HariIT")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMinutes(15)))
                .signWith(SignatureAlgorithm.HS512,secret.getBytes())
                .compact();
    }

    /**2.Read claims or read the token,
       if you want to read the token we need secrete key and token**/
    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
    /**3.return the expiry date**/
    public Date getExpDate(String token){
        return getClaims(token).getExpiration();
    }

    /**4.Read subject/Username**/
    public String getUsername(String token){
     return getClaims(token).getSubject();
    }

    /**5.Validate Exp date**/
    public boolean isTokenExp(String token){
        Date  expDate = getExpDate(token);
        return expDate.before(new Date(System.currentTimeMillis()));
    }

    /**6.Validate username in token and database, exp date
     6.1: here token username and username from database must be same,
       also validating token expiry date. Token should not be expiry ***/
    public boolean validateToken(String token,String userName){
        String tokenUserName = getUsername(token);
        return (userName.equals(tokenUserName)&&isTokenExp(token));
    }
}
