package com.orkva.projects.xmall.auth.client.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

/**
 * JwtUtils
 *
 * @author Shepherd Xie
 * @version 2023/8/29
 */
public class JwtUtils {
    private static final String SECRET_KEY = "Uq23ZsDB46N8G47LRQ62XiPLRi";
    private static final Key SIGNING_KEY;
    private static final Duration DEFAULT_EXPIRATION = Duration.of(2, ChronoUnit.HOURS);

    static {
        byte[] keySecretBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        SIGNING_KEY = new SecretKeySpec(keySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public static String createToken(Object subject) {
        return createToken(subject, DEFAULT_EXPIRATION);
    }

    public static String expired(Object subject) {
        return createToken(subject, Duration.ZERO);
    }

    public static String createToken(Object subject, Duration expiration) {
        return Jwts.builder()
                .setSubject(JsonUtils.writeString(subject))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(expiration)))
                .signWith(SIGNING_KEY)
                .compact();
    }

    public static Claims getPayload(String jwt) {
        return Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(jwt).getBody();
    }

    public static <T> T getSubject(String jwt, Class<T> tClass) {
        return JsonUtils.readBean(getPayload(jwt).getSubject(), tClass);
    }

}
