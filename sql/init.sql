CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL,
                        `name` varchar(50) NOT NULL,
                        `phone` varchar(50) DEFAULT NULL,
                        `email` varchar(100) DEFAULT NULL,
                        `password` varchar(50) DEFAULT NULL,
                        `create_time` datetime DEFAULT NULL,
                        `modify_time` datetime DEFAULT NULL,
                        `modify_by` varchar(50) DEFAULT NULL,
                        `create_by` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_secret` (
                               `id` bigint(20) NOT NULL,
                               `user_id` bigint(20) DEFAULT NULL,
                               `private_key` varchar(1000) DEFAULT NULL,
                               `public_key` varchar(1000) DEFAULT NULL,
                               `custom_key` varchar(255) DEFAULT NULL,
                               `create_by` varchar(50) DEFAULT NULL,
                               `create_time` datetime DEFAULT NULL,
                               `modify_by` varchar(50) DEFAULT NULL,
                               `modify_time` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `secret_custody` (
                                  `id` bigint(20) NOT NULL,
                                  `user_id` bigint(20) NOT NULL,
                                  `name` varchar(255) DEFAULT NULL,
                                  `url` varchar(255) DEFAULT NULL,
                                  `account` varchar(255) DEFAULT NULL,
                                  `password` varchar(255) DEFAULT NULL,
                                  `remark` varchar(255) DEFAULT NULL,
                                  `create_by` varchar(50) DEFAULT NULL,
                                  `create_time` datetime DEFAULT NULL,
                                  `modify_by` varchar(50) DEFAULT NULL,
                                  `modify_time` datetime DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

