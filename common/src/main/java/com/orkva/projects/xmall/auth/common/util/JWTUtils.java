package com.orkva.projects.xmall.auth.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * JWTUtils
 *
 * @author Shepherd Xie
 * @version 2023/8/29
 */
public class JWTUtils {
    private static final String SECRET_KEY = "Uq23ZsDB46N8G47LRQ62XiPLRi";
    private static final Duration DEFAULT_EXPIRATION = Duration.of(2, ChronoUnit.HOURS);

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM_HS256 = SignatureAlgorithm.HS256;

    private static final Key SIGNING_KEY;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        byte[] apiKeySecretBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        SIGNING_KEY = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM_HS256.getJcaName());
    }

    public static String createToken(Object subject) {
        return createToken(subject, DEFAULT_EXPIRATION);
    }

    public static String expired(Object subject) {
        return createToken(subject, Duration.ZERO);
    }

    public static String createToken(Object subject, Duration expiration) {
        try {
            return Jwts.builder()
                    .setSubject(OBJECT_MAPPER.writeValueAsString(subject))
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plus(expiration)))
                    .signWith(SIGNING_KEY)
                    .compact();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Claims getPayload(String jwt) {
        return Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(jwt).getBody();
    }

    public static <T> T getSubject(String jwt, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(getPayload(jwt).getSubject(), tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
