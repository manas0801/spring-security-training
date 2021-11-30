CREATE TABLE IF NOT EXISTS `user` ( `username` VARCHAR(45) NULL,
`password` TEXT NULL,
PRIMARY KEY (`username`));
CREATE TABLE IF NOT EXISTS `userotp` ( `username` VARCHAR(45) NOT NULL,
`otp` VARCHAR(45) NULL,
PRIMARY KEY (`username`));