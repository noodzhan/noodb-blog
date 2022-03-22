SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `category_id` bigint                                  DEFAULT NULL COMMENT '分类id',
    `title`       varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
    `summary`     varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '概要',
    `content`     text COLLATE utf8mb4_general_ci COMMENT '内容',
    `create_by`   bigint                                  DEFAULT NULL COMMENT '创建人',
    `create_time` datetime                                DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint                                  DEFAULT NULL COMMENT '修改人',
    `update_time` datetime                                DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1489082487538737154
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='文章表';



DROP TABLE IF EXISTS `article_images`;
CREATE TABLE `article_images`
(
    `id`          bigint NOT NULL,
    `article_id`  bigint NOT NULL,
    `image_name`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_type`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `base64_url`  blob,
    `system_url`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `create_by`   bigint                                  DEFAULT NULL COMMENT '创建人',
    `create_time` datetime                                DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint                                  DEFAULT NULL COMMENT '修改人',
    `update_time` datetime                                DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`              bigint NOT NULL COMMENT 'id',
    `username`        varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `password`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `phone`           varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `salt`            varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `last_login_time` datetime                                DEFAULT NULL,
    `error_num`       int                                     DEFAULT NULL,
    `last_error_time` datetime                                DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类别名字',
    `pid`         bigint                                  DEFAULT NULL COMMENT '父类别id',
    `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `create_by`   bigint                                  DEFAULT NULL COMMENT '创建人',
    `create_time` datetime                                DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint                                  DEFAULT NULL COMMENT '修改人',
    `update_time` datetime                                DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='类别表';

SET FOREIGN_KEY_CHECKS = 1;
