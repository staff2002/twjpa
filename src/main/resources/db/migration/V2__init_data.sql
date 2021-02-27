INSERT INTO `music`.`artist` VALUES (1,'Queen', '1971-12-01');
INSERT INTO `music`.`artist` VALUES (2,'Metallica', '1981-11-03');
INSERT INTO `music`.`artist` VALUES (3,'Eagles', '1970-01-06');

INSERT INTO `music`.`album` VALUES (1,'A Night at the Opera', '1', 'Hollywood Records', 'Hollywood Records building', '1975-11-01', '2021-02-28', '2021-02-28');
INSERT INTO `music`.`album` VALUES (2,'Ride the Lightning', '2', 'Vertigo', 'Vertigo Office', '1996-10-25', '2021-02-28', '2021-02-28');
INSERT INTO `music`.`album` VALUES (3,'Hotel California', '3', ' Elektra / Wea', ' Elektra / Wea Building', '1976-05-06', '2021-02-28', '2021-02-28');


INSERT INTO `music`.`album_version` (`id`, `album_id`, `version_name`, `medium_type`, `created_at`, `updated_at`) VALUES ('1', '1', '首版黑胶', 'LP', '2021-02-28', '2021-02-28');
INSERT INTO `music`.`album_version` (`id`, `album_id`, `version_name`, `medium_type`, `created_at`, `updated_at`) VALUES ('2', '1', '首版CD', 'CD', '2021-02-28', '2021-02-28');
INSERT INTO `music`.`album_version` (`id`, `album_id`, `version_name`, `medium_type`, `created_at`, `updated_at`) VALUES ('3', '1', '首版磁带', 'TAPE', '2021-02-28', '2021-02-28');
INSERT INTO `music`.`album_version` (`id`, `album_id`, `version_name`, `medium_type`, `created_at`, `updated_at`) VALUES ('4', '2', '首版黑胶', 'LP', '2021-02-28', '2021-02-28');
INSERT INTO `music`.`album_version` (`id`, `album_id`, `version_name`, `medium_type`, `created_at`, `updated_at`) VALUES ('5', '3', '首版CD', 'CD', '2021-02-28', '2021-02-28');


INSERT INTO `music`.`version_image` (`id`, `record_version_id`, `name`, `introduction`, `url`) VALUES ('1', '1', '封面', '封面', 'http://www.music.coom/images/1');
INSERT INTO `music`.`version_image` (`id`, `record_version_id`, `name`, `introduction`, `url`) VALUES ('2', '1', '封底', '封底', 'http://www.music.coom/images/2');
INSERT INTO `music`.`version_image` (`id`, `record_version_id`, `name`, `introduction`, `url`) VALUES ('3', '2', '封面', '封面', 'http://www.music.coom/images/3');

