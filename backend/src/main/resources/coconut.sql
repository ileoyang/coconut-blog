CREATE TABLE IF NOT EXISTS `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `email` varchar(50) NOT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `blog` (
    `id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(50) NOT NULL,
    `content` longtext NOT NULL,
    `user_id` int NOT NULL,
    `view` int NOT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `tag` (
   `id` int NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `blog_tag` (
    `blog_id` int NOT NULL,
    `tag_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `follow` (
    `leader_id` int NOT NULL,
    `follower_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
