# Host: 127.0.0.1  (Version 5.6.35)
# Date: 2017-11-22 16:38:47
# Generator: MySQL-Front 5.4  (Build 4.153) - http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Structure for table "article"
#

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id`            int(11)   NOT NULL AUTO_INCREMENT,
  `content`       varchar(255)       DEFAULT ''
  COMMENT '文章内容',
  `create_time`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `update_time`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  `delete_status` varchar(1)         DEFAULT '1'
  COMMENT '是否有效  1.有效  2无效',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8mb4
  COMMENT ='发布号作者表';

#
# Data for table "article"
#

INSERT INTO `article`
VALUES (5, '莎士比亚', '2017-10-25 09:08:45', '2017-10-30 17:59:41', '1'),
       (6, '亚里士多德', '2017-10-26 10:49:28', '2017-11-18 09:54:15', '1'),
       (10, '亚历山大', '2017-10-26 14:57:45', '2017-11-08 13:28:52', '1'),
       (11, '李白', '2017-10-26 15:23:42', '2017-10-26 15:23:42', '1'),
       (19, '文章test2', '2017-11-18 13:37:07', '2017-11-18 13:37:11', '1');

#
# Structure for table "sys_permission"
#

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id`              bigint(20) NOT NULL AUTO_INCREMENT
  COMMENT '权限表id,自增主键',
  `menu_code`       varchar(255)        DEFAULT ''
  COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name`       varchar(255)        DEFAULT ''
  COMMENT '菜单的中文释义',
  `permission_code` varchar(255)        DEFAULT ''
  COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255)        DEFAULT ''
  COMMENT '本权限的中文释义',
  `available`       bool                default false,
  `gmt_create`      timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified`    timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  `version`         bigint(20)          DEFAULT NULL
  COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = COMPACT
  COMMENT ='后台权限表';

#
# Data for table "sys_permission"
#

INSERT INTO `sys_permission`
VALUES (101, 'article', '文章管理', 'article:list', '列表', 1, null, null, 1),
       (102, 'article', '文章管理', 'article:add', '新增', 1, null, null, 1),
       (103, 'article', '文章管理', 'article:update', '修改', 1, null, null, 1),
       (601, 'user', '用户', 'user:list', '列表', 1, null, null, 1),
       (602, 'user', '用户', 'user:add', '新增', 1, null, null, 1),
       (603, 'user', '用户', 'user:update', '修改', 1, null, null, 1),
       (701, 'role', '角色权限', 'role:list', '列表', 1, null, null, 1),
       (702, 'role', '角色权限', 'role:add', '新增', 1, null, null, 1),
       (703, 'role', '角色权限', 'role:update', '修改', 1, null, null, 1),
       (704, 'role', '角色权限', 'role:delete', '删除', 1, null, null, 1);

#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id`           bigint(20) NOT NULL AUTO_INCREMENT
  COMMENT '角色表id,自增主键',
  `sys_role`     varchar(255)        DEFAULT ''
  COMMENT '角色名',
  `description`  varchar(255)        DEFAULT ''
  COMMENT '描述',
  `available`    bool                default false,
  `gmt_create`   timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  `version`      bigint(20)          DEFAULT NULL
  COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = COMPACT
  COMMENT ='后台角色表';

#
# Data for table "sys_role"
#

INSERT INTO `sys_role`
VALUES (1, '管理员', '',  1, null, null, 1),
       (2, '作家', '',  1, null, null, 1),
       (3, '程序员', '',  1, null, null, 1);

#
# Structure for table "sys_role_permission"
#

DROP TABLE IF EXISTS `sys_role_permissions`;
CREATE TABLE `sys_role_permissions` (
  `id`            bigint(20) NOT NULL AUTO_INCREMENT
  COMMENT '关联表id,自增主键',
  `role_id`       bigint(20)          DEFAULT NULL
  COMMENT '角色id',
  `permission_id` bigint(20)          DEFAULT NULL
  COMMENT '权限id',
  `available`     bool                default false,
  `gmt_create`    timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified`  timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  `version`       bigint(20)          DEFAULT NULL
  COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  constraint sys_role_permissions UNIQUE key (role_id, permission_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = COMPACT
  COMMENT ='角色-权限关联表';

#
# Data for table "sys_role_permission"
#

INSERT INTO `sys_role_permissions`
VALUES (1, 2, 101, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (2, 2, 102, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (5, 2, 602, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (6, 2, 601, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (7, 2, 603, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (8, 2, 703, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (9, 2, 701, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (10, 2, 702, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (11, 2, 704, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (12, 2, 103, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (13, 3, 601, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (14, 3, 701, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (15, 3, 702, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (16, 3, 704, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (17, 3, 102, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (18, 3, 101, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (19, 3, 603, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1);

#
# Structure for table "sys_user"
#

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id`           bigint(20) NOT NULL AUTO_INCREMENT
  COMMENT '用户表id,自增主键',
  `username`     varchar(255)        DEFAULT NULL
  COMMENT '用户名',
  `password`     varchar(255)        DEFAULT NULL
  COMMENT '密码',
  `salt`     varchar(255)        DEFAULT NULL
  COMMENT '盐',
  `nickname`     varchar(255)        DEFAULT NULL
  COMMENT '昵称',
  `available`    bool                default false,
  `gmt_create`   timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  `version`      bigint(20)          DEFAULT NULL
  COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = COMPACT
  COMMENT ='用户表';

#
# Data for table "sys_user"
#
INSERT INTO `sys_user`
VALUES (10003, 'admin', '123456','',  '超级用户23', 1, '2017-10-30 11:52:38', '2017-11-17 23:51:40', 1),
       (10004, 'user', '123456','', '莎士比亚', 1, '2017-10-30 16:13:02', '2017-11-18 02:48:24', 1),
       (10005, 'aaa', '123456','', 'abba', 1, '2017-11-15 14:02:56', '2017-11-17 23:51:42', 1),
       (10007, 'test', '123456','', '就看看列表', 1, '2017-11-22 16:29:41', '2017-11-22 16:29:41', 1);

DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id`           bigint(20) NOT NULL AUTO_INCREMENT
  COMMENT '关联表id,自增主键',
  `role_id`      bigint(20) NOT NULL
  COMMENT '角色id',
  `user_id`      bigint(20) NOT NULL
  COMMENT '用户id',
  `available`    bool                default false,
  `gmt_create`   timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  `version`      bigint(20)          DEFAULT NULL
  COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  constraint pk_sys_users_roles UNIQUE key (user_id, role_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = COMPACT
  COMMENT ='用户-角色关联表';


INSERT INTO `sys_user_roles`
VALUES (1, 10003, 1, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (2, 10004, 2, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (3, 10005, 1, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1),
       (4, 10007, 3, 1, '2017-11-22 16:26:21', '2017-11-22 16:26:32', 1)
