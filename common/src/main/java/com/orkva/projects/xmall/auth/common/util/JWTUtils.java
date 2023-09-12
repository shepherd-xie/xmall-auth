package com.orkva.projects.xmall.auth.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Duration;
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
    private static final String SECRET_KEY = "secret_slot";
    private static final Duration DEFAULT_EXPIRATION = Duration.of(2, ChronoUnit.HOURS);

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM_HS256 = SignatureAlgorithm.HS256;

    private static final Key SIGNING_KEY;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        SIGNING_KEY = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM_HS256.getJcaName());
    }

    public static String createToken(UserDetails userDetails) {
        return createToken(userDetails, DEFAULT_EXPIRATION);
    }

    public static String expired(UserDetails userDetails) {
        return createToken(userDetails, Duration.ZERO);
    }

    public static String createToken(Object subject, Duration expiration) {
        try {
            return Jwts.builder()
                    .setSubject(OBJECT_MAPPER.writeValueAsString(subject))
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plus(expiration)))
                    .signWith(SIGNATURE_ALGORITHM_HS256, SIGNING_KEY)
                    .compact();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Claims getPayload(String jwt) {
        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(jwt).getBody();
    }

    public static UserDetails getUserDetails(String jwt) {
        try {
            return OBJECT_MAPPER.readValue(getPayload(jwt).getSubject(), UserDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
