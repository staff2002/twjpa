-- prepare data for delete
INSERT INTO `music`.`artist` VALUES (100,'Bob Dylan', '1961-01-01');
INSERT INTO `music`.`album` VALUES (100,'Like a Rolling Stone', '100', 'Columbia', 'Columbia building', '1965-06-01', '2021-02-28', '2021-02-28');

INSERT INTO `music`.`album_version` VALUES ('100', '100', '首版黑胶', 'LP', '2021-02-28', '2021-02-28');
INSERT INTO `music`.`album_version` VALUES ('101', '100', '首版CD', 'CD', '2021-02-28', '2021-02-28');
INSERT INTO `music`.`album_version` VALUES ('101', '100', '首版磁带', 'CD', '2021-02-28', '2021-02-28');

-- delete data
delete from album where id = 100;
delete from artist where id = 100;
delete from album_version where id in ('100','101','102')

