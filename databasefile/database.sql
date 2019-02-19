SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `photogallery` ;
CREATE SCHEMA IF NOT EXISTS `photogallery` DEFAULT CHARACTER SET utf8 ;
USE `photogallery` ;


DROP TABLE IF EXISTS `user` ;
CREATE TABLE IF NOT EXISTS `user` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`password` VARCHAR(100) NOT NULL,
`email` VARCHAR(100) NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

DROP TABLE IF EXISTS `folder` ;
CREATE TABLE IF NOT EXISTS `folder` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`foldername` VARCHAR(100) DEFAULT NULL,
`files` VARCHAR(100) DEFAULT NULL,
`usrid` BIGINT DEFAULT NULL,
PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

DROP TABLE IF EXISTS `picture` ;
CREATE TABLE IF NOT EXISTS `picture` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`picturepath` VARCHAR(100) NOT NULL,
`folderid` BIGINT NOT NULL,
`checkbox` BIGINT NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

INSERT INTO `photogallery`.`user` (`password`, `email`) VALUES ('1234', 'photogallerybootcamp@gmail.com');
INSERT INTO `photogallery`.`folder` (`foldername`, `usrid`) VALUES ('adminfolder', '1002');
INSERT INTO `photogallery`.`picture` (`picturepath`, `folderid`, `checkbox`) VALUES ('https://hsto.org/getpro/habr/post_images/a60/848/42d/a6084842dd1354a400e1719a817f9bba.jpg', '1002', '0');

