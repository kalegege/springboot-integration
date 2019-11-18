CREATE DATABASE mm;
USE mm;

CREATE TABLE sys_file_info (
    `fileName`              VARCHAR(64)       NOT NULL                                    COMMENT '文件名称',
    `file_type`             INT UNSIGNED      NOT NULL                                    COMMENT '文件类型',
    `filePath`              VARCHAR(128)      NOT NULL                                    COMMENT '文件路径',
    `fileGroup`             VARCHAR(128)      NOT NULL                                    COMMENT '文件存储组',
    `fileSize`              BIGINT UNSIGNED   NOT NULL                                    COMMENT '文件大小',

    `id`                    BIGINT UNSIGNED   NOT NULL                                    COMMENT '主键',
    `ctime`                 TIMESTAMP         NOT NULL                                    COMMENT '创建时间',
    `mtime`                 TIMESTAMP         NOT NULL                                    COMMENT '修改时间',
    `rtime`                 TIMESTAMP         NOT NULL                                    COMMENT '系统调整时间',
    `isvalid`               INT UNSIGNED      NOT NULL                                    COMMENT '是否游侠',
    `creater`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建人ID',
    `updater`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新者ID',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';


CREATE TABLE wb_report (
    `reportType`            VARCHAR(64)       NOT NULL                                    COMMENT '名称',
    `reportTypeName`        VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `reportCode`            VARCHAR(64)       NOT NULL                                    COMMENT '类型',
    `reportName`            VARCHAR(64)       NOT NULL                                    COMMENT '键名',
    `grade`                 VARCHAR(64)       NOT NULL                                    COMMENT '所属入口',
    `wbReportGradeId`       BIGINT UNSIGNED   NOT NULL                                    COMMENT '名称',
    `reportTitle`           VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `reportDesc`            VARCHAR(64)       NOT NULL                                    COMMENT '类型',
    `reportKeyword`         VARCHAR(64)       NOT NULL                                    COMMENT '键名',
    `relaReportId`          BIGINT UNSIGNED   NOT NULL                                    COMMENT '所属入口',
    `importUser`            BIGINT UNSIGNED   NOT NULL                                    COMMENT '名称',
    `userName`              VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `mechanism`             VARCHAR(64)       NOT NULL                                    COMMENT '类型',
    `author`                VARCHAR(64)       NOT NULL                                    COMMENT '键名',
    `industry`              BIGINT UNSIGNED   NOT NULL                                    COMMENT '所属入口',
    `indeustryName`         VARCHAR(64)       NOT NULL                                    COMMENT '名称',
    `isPositive`            TINYINT UNSIGNED  NOT NULL DEFAULT ''                         COMMENT '描述',
    `startTime`             TIMESTAMP         NOT NULL                                    COMMENT '类型',
    `endTime`               TIMESTAMP         NOT NULL                                    COMMENT '键名',
    `order`                 VARCHAR(128)      NOT NULL                                    COMMENT '所属入口',
    `realReportName`        VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `filePath`              VARCHAR(64)       NOT NULL                                    COMMENT '类型',
    `reportSource`          INT UNSIGNED      NOT NULL                                    COMMENT '键名',
    `reportUrl`             VARCHAR(64)       NOT NULL                                    COMMENT '所属入口',
    `originalIndustry`      VARCHAR(64)       NOT NULL                                    COMMENT '名称',

    `id`                    BIGINT UNSIGNED   NOT NULL                                    COMMENT '主键',
    `ctime`                 TIMESTAMP         NOT NULL                                    COMMENT '创建时间',
    `mtime`                 TIMESTAMP         NOT NULL                                    COMMENT '修改时间',
    `rtime`                 TIMESTAMP         NOT NULL                                    COMMENT '系统调整时间',
    `isvalid`               INT UNSIGNED      NOT NULL                                    COMMENT '是否游侠',
    `creater`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建人ID',
    `updater`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新者ID',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`id`),
    UNIQUE  KEY `name` (`name`),
    INDEX       `type` (`type`) USING BTREE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='外部报告表';


CREATE TABLE t_treasure_role (
    `id`                    BIGINT UNSIGNED   NOT NULL                                    COMMENT '主键',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '名称,唯一不可重复',
    `description`           VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`id`),
    UNIQUE  KEY `name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';


CREATE TABLE  t_treasure_access (
    `id`                    BIGINT UNSIGNED   NOT NULL                                    COMMENT 'ID',
    `role_id`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '角色ID',
    `entrance_id`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '入口ID',
    `domain_id`             BIGINT UNSIGNED   NOT NULL                                    COMMENT '领域ID',
    `value`                 VARCHAR(64)       NOT NULL                                    COMMENT '可访问领域值',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='允许访问的领域值表';


CREATE TABLE t_treasure_dictionary (
    `key`                   VARCHAR(64)       NOT NULL                                    COMMENT '键',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '名称,唯一不可重复',
    `type`                  VARCHAR(64)       NOT NULL                                    COMMENT '类型',
    `value`                 VARCHAR(64)       NOT NULL                                    COMMENT '值',
    `online`                TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否已上线，0：未上线，1：已上线，默认否',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`key`),
    UNIQUE  KEY `name` (`name`),
    INDEX       `type` (`type`) USING BTREE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';


create table t_treasure_area (
    `code`                  VARCHAR(64)       NOT NULL                                    COMMENT '编码',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '名称',
    `mapping_code`          VARCHAR(64)       NOT NULL                                    COMMENT '一级能开编码',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`code`),
    UNIQUE  KEY `mapping_code` (`mapping_code`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行政区表';


CREATE TABLE t_treasure_user (
    `id`                    BIGINT UNSIGNED   NOT NULL                                    COMMENT '主键',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '姓名',
    `account`               VARCHAR(64)       NOT NULL                                    COMMENT '账号',
    `password_hash`         VARCHAR(64)       NOT NULL                                    COMMENT '哈希密码',
    `mobile`                VARCHAR(16)       NOT NULL DEFAULT ''                         COMMENT '手机号',
    `mail`                  VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '邮箱',
    `status`                VARCHAR(64)       NOT NULL DEFAULT 'INACTIVE'                 COMMENT '状态，默认不激活',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`id`),
    UNIQUE  KEY `account` (`account`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


CREATE TABLE t_treasure_application (
    `code`                  VARCHAR(64)       NOT NULL                                    COMMENT '编号',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '名称',
    `description`           VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `contacts_name`         VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '联系人姓名',
    `contacts_mobile`       VARCHAR(16)       NOT NULL DEFAULT ''                         COMMENT '联系人手机号',
    `online`                TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否已上线，0：未上线，1：已上线，默认否',
    `public_key`            VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '应用DSA公钥',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`code`),
    UNIQUE  KEY `name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';


CREATE TABLE t_treasure_channel (
    `code`                  VARCHAR(64)       NOT NULL                                    COMMENT '编号',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '名称',
    `description`           VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `contacts_name`         VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '联系人姓名',
    `contacts_mobile`       VARCHAR(16)       NOT NULL DEFAULT ''                         COMMENT '联系人手机号',
    `online`                TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否已上线，0：未上线，1：已上线，默认否',
    `parent_code`           VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '上级渠道编号，默认无',
    `level`                 TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '渠道等级，默认一级渠道',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`code`),
    UNIQUE  KEY `name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道表';


CREATE TABLE t_treasure_supplier (
    `code`                  VARCHAR(64)       NOT NULL                                    COMMENT '编号',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '名称',
    `description`           VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `contacts_name`         VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '联系人姓名',
    `contacts_mobile`       VARCHAR(16)       NOT NULL DEFAULT ''                         COMMENT '联系人手机号',
    `company`               VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '所属公司',
    `bank_account`          VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '银行账号',
    `ability`               VARCHAR(64)       NOT NULL                                    COMMENT '供应商能力配置',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`code`),
    UNIQUE  KEY `name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商表';


CREATE TABLE t_treasure_goods (
    `code`                  VARCHAR(64)       NOT NULL                                    COMMENT '编号',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '名称',
    `description`           VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `online`                TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否已上线，0：未上线，1：已上线，默认否',
    `supplier_code`         VARCHAR(64)       NOT NULL                                    COMMENT '供应商编码',
    `supplier_goods_code`   VARCHAR(64)       NOT NULL                                    COMMENT '供应商商品编码',
    `supplier_price`        INT UNSIGNED      NOT NULL                                    COMMENT '供应价，单位：分',
    `is_resource`           TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否资源类型商品，默认否',
    `order_mode`            VARCHAR(64)       NOT NULL DEFAULT 'INTERNAL'                 COMMENT '订购方式，默认INTERNAL，内部订购',
    `origin_stock`          INT UNSIGNED      NOT NULL                                    COMMENT '总库存，单位：份',
    `remain_stock`          INT UNSIGNED      NOT NULL                                    COMMENT '剩余库存，单位：份',
    `stock_alarm_line`      INT UNSIGNED      NOT NULL                                    COMMENT '库存预警，单位：份',
    `image`                 VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '商品活动图片',
    `label`                 VARCHAR(64)       NOT NULL DEFAULT 'NEW'                      COMMENT '商品标签，前端可关联角标',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`code`),
    UNIQUE  KEY `name` (`name`),
    INDEX       `supplier_code` (`supplier_code`) USING BTREE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';


CREATE TABLE t_treasure_activity (
    `code`                  VARCHAR(64)       NOT NULL                                    COMMENT '编号',
    `name`                  VARCHAR(64)       NOT NULL                                    COMMENT '名称',
    `description`           VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '描述',
    `contacts_name`         VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '联系人姓名',
    `contacts_mobile`       VARCHAR(16)       NOT NULL DEFAULT ''                         COMMENT '联系人手机号',
    `online`                TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否已上线，0：未上线，1：已上线，默认否',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`code`),
    UNIQUE  KEY `name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动表';


CREATE TABLE t_treasure_order (
    `id`                    BIGINT UNSIGNED   NOT NULL                                    COMMENT '系统唯一订单号',
    `channel_code`          VARCHAR(64)       NOT NULL                                    COMMENT '渠道编码',
    `channel_order_id`      VARCHAR(64)       NOT NULL                                    COMMENT '渠道唯一订单号',
    `business_account`      VARCHAR(64)       NOT NULL                                    COMMENT '订购账号',
    `business_account_type` VARCHAR(64)       NOT NULL                                    COMMENT '账号类型',
    `business_mobile`       VARCHAR(16)       NOT NULL DEFAULT ''                         COMMENT '业务手机号',
    `area_code`             VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '业务行政区编码',
    `area_name`             VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '业务行政区名称',

    `goods_code`            VARCHAR(64)       NOT NULL                                    COMMENT '商品编码',
    `goods_name`            VARCHAR(64)       NOT NULL                                    COMMENT '商品名称',
    `is_resource`           TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否资源类型商品，默认否',
    `resource_code`         VARCHAR(64)       NOT NULL                                    COMMENT '绑定资源编号',

    `status`                VARCHAR(64)       NOT NULL DEFAULT 'PENDING'                  COMMENT '订单状态',
    `explanation`           VARCHAR(128)      NOT NULL DEFAULT ''                         COMMENT '解释',
    `notice_address`        VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '订购结果通知地址',
    `next_notice_time`      BIGINT UNSIGNED   NOT NULL DEFAULT 0                          COMMENT '下一次通知时间',
    `remain_notice_times`   TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '剩余通知次数',

    `activity_code`         VARCHAR(64)       NOT NULL                                    COMMENT '活动编码',
    `activity_price`        INT UNSIGNED      NOT NULL                                    COMMENT '活动价格，单位：分',
    `supplier_code`         VARCHAR(64)       NOT NULL                                    COMMENT '供应方编码',
    `supplier_goods_code`   VARCHAR(64)       NOT NULL                                    COMMENT '供应商商品编码',
    `supplier_price`        INT UNSIGNED      NOT NULL                                    COMMENT '活动价格，单位：分',
    `supplier_order_id`     VARCHAR(64)       NOT NULL DEFAULT ''                         COMMENT '供应方唯一订单号',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`id`),
    INDEX       `channel_code` (`channel_code`) USING BTREE,
    INDEX       `area_code` (`area_code`) USING BTREE,
    INDEX       `goods_code` (`goods_code`) USING BTREE,
    INDEX       `status` (`status`) USING BTREE,
    INDEX       `activity_code` (`activity_code`) USING BTREE,
    INDEX       `supplier_code` (`supplier_code`) USING BTREE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';


CREATE TABLE  r_treasure_role_entrance (
    `role_id`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '角色ID',
    `entrance_id`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '入口ID',
    `domain_confine_on`     TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否开启领域访问限制，默认否',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`role_id`,`entrance_id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与可访问入口关系表';


CREATE TABLE  r_treasure_role_domain (
    `role_id`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '角色ID',
    `domain_id`             BIGINT UNSIGNED   NOT NULL                                    COMMENT '领域ID',
    `entrance_id`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '入口ID',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`role_id`,`domain_id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与领域访问控制关系表';


CREATE TABLE  r_treasure_role_user (
    `role_id`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '角色ID',
    `user_id`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '用户ID',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`role_id`,`user_id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与用户关系表';


CREATE TABLE  r_treasure_role_application (
    `role_id`               BIGINT UNSIGNED   NOT NULL                                    COMMENT '角色ID',
    `application_code`      VARCHAR(64)       NOT NULL                                    COMMENT '应用编号',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`role_id`,`application_code`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与应用关系表';


CREATE TABLE  r_treasure_application_channel (
    `application_code`      VARCHAR(64)       NOT NULL                                    COMMENT '应用编号',
    `channel_code`          VARCHAR(64)       NOT NULL                                    COMMENT '渠道编号',
    `is_owner`              TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '应用是否归属该渠道，默认否',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`application_code`,`channel_code`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用与渠道关系表';


CREATE TABLE  r_treasure_activity_channel (
    `activity_code`         VARCHAR(64)       NOT NULL                                    COMMENT '活动编码',
    `channel_code`          VARCHAR(64)       NOT NULL                                    COMMENT '渠道编码',
    `online`                TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '活动是否已发布到该渠道，默认否',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`activity_code`,`channel_code`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动与渠道关系表';


CREATE TABLE  r_treasure_activity_goods (
    `activity_code`         VARCHAR(64)       NOT NULL                                    COMMENT '活动编码',
    `goods_code`            VARCHAR(64)       NOT NULL                                    COMMENT '商品编码',
    `online`                TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '商品是否已发布到活动，默认否',
    `origin_stock`          INT UNSIGNED      NOT NULL                                    COMMENT '总库存，单位：份',
    `remain_stock`          INT UNSIGNED      NOT NULL                                    COMMENT '剩余库存，单位：份',
    `stock_alarm_line`      INT UNSIGNED      NOT NULL                                    COMMENT '库存预警，单位：份',
    `sequence`              INT UNSIGNED      NOT NULL                                    COMMENT '序号',
    `image`                 VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '商品活动图片',
    `label`                 VARCHAR(64)       NOT NULL DEFAULT 'NORMAL'                   COMMENT '商品标签，前端可关联角标',

    `create_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '创建时间',
    `creator`               VARCHAR(64)       NOT NULL                                    COMMENT '创建者',
    `update_time`           BIGINT UNSIGNED   NOT NULL                                    COMMENT '更新时间',
    `updater`               VARCHAR(64)       NOT NULL                                    COMMENT '更新者',
    `deleted`               TINYINT UNSIGNED  NOT NULL DEFAULT 0                          COMMENT '是否逻辑删除',
    `remark`                VARCHAR(1024)     NOT NULL DEFAULT ''                         COMMENT '备注，DBA操作使用',

    PRIMARY KEY (`activity_code`,`goods_code`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动与商品关系表';