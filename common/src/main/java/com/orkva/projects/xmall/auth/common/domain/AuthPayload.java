package com.orkva.projects.xmall.auth.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * AuthPayload
 *
 * @author Shepherd Xie
 * @version 2023/9/19
 */
@Data
public class AuthPayload<T> implements Serializable {
    private String id;
    private T info;
    private Instant expiration;
}
