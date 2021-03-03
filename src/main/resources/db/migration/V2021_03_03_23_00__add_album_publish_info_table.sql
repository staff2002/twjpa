CREATE TABLE `album_publish_info` (
                     `id` bigint(20) NOT NULL AUTO_INCREMENT,
                     `album_id` bigint(20) NOT NULL ,
                     `publisher` varchar(200) NOT NULL ,
                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
