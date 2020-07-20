package com.example.demo.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;
import java.util.Date;

public class JwtHelper {
    static KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.ES256);
    static long activityTime = 1000 * 60 * 60 * 24; //ms  1 day

    static public String createToken(String username) {
        return createToken(username, new Date(System.currentTimeMillis() + activityTime));
    }

    static public String createToken(String username, Date endDate) {
        String compact = Jwts.builder().setSubject(username).setExpiration(endDate).signWith(keyPair.getPrivate()).compact();
        return compact;
    }

    static public String parseToken(String token) {
        Jws<Claims> jws = null;
        try {
            jws = Jwts.parserBuilder().setSigningKey(keyPair.getPublic()).build().parseClaimsJws(token);
            return jws.getBody().getSubject();
        } catch (JwtException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
