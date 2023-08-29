DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `username`     VARCHAR(255) UNIQUE                 NOT NULL,
    `email`        VARCHAR(255) UNIQUE                 NOT NULL,
    `password`     VARCHAR(255)                        NOT NULL,
    `enable`       BOOLEAN   DEFAULT TRUE              NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_profile`;
CREATE TABLE `tb_profile`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `user_id`      BIGINT                              NOT NULL,
    `nickname`     VARCHAR(255)                        NOT NULL,
    `phone_number` VARCHAR(255)                        NOT NULL,
    `time_zone`    VARCHAR(255)                        NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_roles`;
CREATE TABLE `tb_roles`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `value`        VARCHAR(255) UNIQUE                 NOT NULL,
    `name`         VARCHAR(255)                        NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_permissions`;
CREATE TABLE `tb_permissions`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `value`        VARCHAR(255) UNIQUE                 NOT NULL,
    `name`         VARCHAR(255)                        NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_user_roles`;
CREATE TABLE `tb_user_roles`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `user_id`      BIGINT                              NOT NULL,
    `role_id`      BIGINT                              NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_role_permissions`;
CREATE TABLE `tb_role_permissions`
(
    `id`            BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `role_id`       BIGINT                              NOT NULL,
    `permission_id` BIGINT                              NOT NULL,
    `created_date`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_date`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;