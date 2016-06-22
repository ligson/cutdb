SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` ( `id` varchar(255) NOT NULL, `name` varchar(255) NOT NULL, PRIMARY KEY (`id`), UNIQUE KEY `UK_1tnqexlrgntt1f84w1g0u9njb` (`name`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` ( `id` varchar(255) NOT NULL, `createDate` datetime DEFAULT NULL, `name` varchar(255) NOT NULL, `password` varchar(255) NOT NULL, `sex` bit(1) DEFAULT NULL, `org_id` varchar(255) DEFAULT NULL,PRIMARY KEY (`id`), UNIQUE KEY `UK_g8gqk4e142wekcb1t6d3v2mwx` (`name`), KEY `FKo9v0dtp74vlb6xr849o7je1x6` (`org_id`), CONSTRAINT `FKo9v0dtp74vlb6xr849o7je1x6` FOREIGN KEY (`org_id`) REFERENCES `t_org` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
