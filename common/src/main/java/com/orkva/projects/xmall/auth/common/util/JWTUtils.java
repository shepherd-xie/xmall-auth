package com.orkva.projects.xmall.auth.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * JWTUtils
 *
 * @author Shepherd Xie
 * @version 2023/8/29
 */
public class JWTUtils {
    private static String secret;

    public static String createToken(String payload) {
        return Jwts.builder()
                .setPayload(payload)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static Object getPayload(String token) {
        return Jwts.parser().parse(token).getBody();
    }

}
