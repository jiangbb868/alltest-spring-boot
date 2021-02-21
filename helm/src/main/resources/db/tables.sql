CREATE TABLE IF NOT EXISTS `tbl_user`(
   `user_id` INT UNSIGNED AUTO_INCREMENT,
   `user_name` VARCHAR(100) NOT NULL,
   `age` INT NOT NULL,
   `birthday` DATE,
   PRIMARY KEY ( `user_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;