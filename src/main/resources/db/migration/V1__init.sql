CREATE TABLE `album` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `name` varchar(50) NOT NULL,
                       `artist_id` bigint(20) NOT NULL,
                       `company_name` varchar(45) DEFAULT NULL,
                       `brand_id` bigint(20) NOT NULL,
                       `sn` varchar(10) NOT NULL,
                       `first_publish_data` date DEFAULT NULL,
                       `medium_types` varchar(45) DEFAULT NULL,
                       `introduction` varchar(3000) DEFAULT NULL,
                       `cover_image_url` varchar(200) DEFAULT NULL,
                       `cover_image_url_original` varchar(200) DEFAULT NULL,
                       `tags` varchar(200) DEFAULT NULL,
                       `style` varchar(50) DEFAULT NULL,
                       `created_at` datetime DEFAULT NULL,
                       `updated_at` datetime DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=784 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `artist` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) NOT NULL,
                        `founding_date` datetime DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `brand` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `name` varchar(50) NOT NULL,
                       `code` varchar(10) DEFAULT NULL,
                       `founding_date` datetime DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `album_version` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `album_id` bigint(20) NOT NULL,
                               `version_type` varchar(10) DEFAULT NULL,
                               `version_name` varchar(50) DEFAULT NULL,
                               `introduction` varchar(1000) DEFAULT NULL,
                               `publish_date` datetime DEFAULT NULL,
                               `medium_type` varchar(10) DEFAULT NULL,
                               `ifpi` varchar(10) DEFAULT NULL,
                               `created_at` datetime DEFAULT NULL,
                               `updated_at` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;


