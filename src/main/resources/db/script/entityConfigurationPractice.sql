-- prepare data for delete
CREATE TABLE `track`
(
  `id`                        bigint(20) NOT NULL AUTO_INCREMENT,
  `album_id` bigint(20) NOT NULL,
  `title`                     varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


INSERT INTO `music`.`track` (`id`, `album_id`, `title`) VALUES ('1', '1', 'song1');
INSERT INTO `music`.`track` (`id`, `album_id`, `title`) VALUES ('2', '1', 'song2');
INSERT INTO `music`.`track` (`id`, `album_id`, `title`) VALUES ('3', '1', 'song3');

