CREATE TABLE `tag` (
                     `id` bigint(20) NOT NULL AUTO_INCREMENT,
                     `name` varchar(20) NOT NULL,
                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `album_tag_relation` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                    `album_id` bigint(20) NOT NULL,
                                    `tag_id` bigint(20) NOT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `music`.`tag` VALUES (1,'著名乐队');
INSERT INTO `music`.`tag` VALUES (2,'TOP100');
INSERT INTO `music`.`tag` VALUES (3,'好听');

INSERT INTO `music`.`album_tag_relation` VALUES (1,1,1);
INSERT INTO `music`.`album_tag_relation` VALUES (2,1,2);
INSERT INTO `music`.`album_tag_relation` VALUES (3,1,3);

INSERT INTO `music`.`album_tag_relation` VALUES (4,2,1);
INSERT INTO `music`.`album_tag_relation` VALUES (5,2,2);

INSERT INTO `music`.`album_tag_relation` VALUES (6,3,1);
