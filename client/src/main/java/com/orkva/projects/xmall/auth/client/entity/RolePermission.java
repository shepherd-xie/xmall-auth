package com.orkva.projects.xmall.auth.client.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;

import java.time.Instant;

/**
 * RolePermission
 *
 * @author Shepherd Xie
 * @version 2023/7/30
 */
@Entity
@Data
@Table(name = "tb_role_permissions")
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant updatedDate;
}
