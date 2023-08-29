package com.orkva.projects.xmall.auth.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;

import java.time.Instant;

/**
 * Profile
 *
 * @author Shepherd Xie
 * @version 2023/7/30
 */
@Entity
@Data
@Table(name = "tb_profile")
public class Profile {
    @Column(unique = true)
    private Integer userId;
    private String nickname;
    private String phoneNumber;
    private String timeZone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant updatedDate;
}
