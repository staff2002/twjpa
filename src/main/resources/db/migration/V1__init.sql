CREATE TABLE `album` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `name` varchar(50) NOT NULL,
                       `artist_id` bigint(20) NOT NULL,
                       `company_name` varchar(45) DEFAULT NULL,
                       `company_address` varchar(45) DEFAULT NULL,
                       `publish_data` date DEFAULT NULL,
                       `created_at` datetime DEFAULT NULL,
                       `updated_at` datetime DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `artist` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) NOT NULL,
                        `founding_date` datetime DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `album_version` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `album_id` bigint(20) NOT NULL,
                               `version_name` varchar(50) DEFAULT NULL,
                               `medium_type` varchar(10) DEFAULT NULL,
                               `created_at` datetime DEFAULT NULL,
                               `updated_at` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `version_image` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `record_version_id` bigint(20) NOT NULL,
                               `name` varchar(45) DEFAULT NULL,
                               `introduction` varchar(500) DEFAULT NULL,
                               `url` varchar(45) NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


