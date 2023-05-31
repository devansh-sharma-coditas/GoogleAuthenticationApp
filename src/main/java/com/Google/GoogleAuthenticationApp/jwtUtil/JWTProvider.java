package com.Google.GoogleAuthenticationApp.jwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Date;
import java.util.Map;

public class JWTProvider {
    @Autowired
    Environment environment;
    String secret = environment.getProperty("secret.key");

    public String jwtTokenGenerator(OAuth2User oAuth2User ){
        Date issuedAt = new Date();
        Date validity = new Date(issuedAt.getTime()+ 5*1000);

        Map<String, Object> claims = oAuth2User.getAttributes();

        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setExpiration(validity)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }
    public String invalidateToken(String token){
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);

        return Jwts.builder()
                .setIssuedAt(claims.getBody().getIssuedAt())
                .setClaims(claims.getBody())
                .setExpiration(new Date(claims.getBody().getExpiration().getTime()-1000))
                .signWith(SignatureAlgorithm.HS256 , secret)
                .compact();
    }
}
