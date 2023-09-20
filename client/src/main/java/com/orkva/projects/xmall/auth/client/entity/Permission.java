package com.orkva.projects.xmall.auth.client.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

/**
 * Permission
 *
 * @author Shepherd Xie
 * @version 2023/7/30
 */
@Entity
@Data
@Table(name = "tb_permissions")
public class Permission {
    @Column(unique = true)
    private String value;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant updatedDate;
}
