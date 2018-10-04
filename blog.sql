#SELECT id, name FROM product WHERE 87 = id;
#UPDATE product SET originalPrice = originalPrice/1.5 WHERE id = 87;
#SHOW CREATE TABLE product;
#DESCRIBE order_;
#SELECT o.id, p.name FROM order_ o INNER JOIN orderitem oi ON o.id = oi.oid INNER JOIN product p ON oi.pid = p.id ORDER BY p.name;
#SELECT p1.*, p1.promotePrice - AVG(p2.promotePrice) AS differenceFromAvgPrice, AVG(p2.promotePrice) FROM product AS p1 CROSS JOIN product AS p2 GROUP BY p1.id;
#insert INTO order_ VALUES (1,'201608241638122609867','某某市，某某区，某某街道，某某号 ','610000','某某某','15111111111',NULL,'2016-12-30',NULL,NULL,NULL,1,'waitDelivery');
#CREATE USER 'qiujintao'@'%' IDENTIFIED BY 'ck6531129ZXCVMU!!!';
#GRANT ALL PRIVILEGES ON *.* TO 'qiujintao'@'%' WITH GRANT OPTION;
#FLUSH PRIVILEGES;
CREATE DATABASE `blog`;
DROP TABLE IF EXISTS `blog`.`user`;
CREATE TABLE IF NOT EXISTS `blog`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lastName` VARCHAR(20) NULL,
  `firstName` VARCHAR(10) NULL,
  `nickName` VARCHAR(45) NOT NULL,
  `passwordHash` VARCHAR(60) NOT NULL,
  `image` BLOB NULL,
  `active` TINYINT(1) DEFAULT 1,
  `role` VARCHAR(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `nickName_UNIQUE` (`nickName` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `blog`.`user` VALUES (1,'邱','锦涛','alan','$2a$10$42nXFgpdRIHO.SFMvUoAHe8P2SFCUUPBjzsmtdf.TA6k84Cb2g5Km',NULL,1,'ADMIN','1531508001@qq.com'),(3,NULL,NULL,'jack','$2a$10$TWEl2UOYW4Ei8Ta2L79gGe77OGf7.H3SoHi4dtbf7I74ZSg71RH9q',NULL,1,'USER','alanharperironharper@gmail.com');

DROP TABLE IF EXISTS `blog`.`article`;
CREATE TABLE IF NOT EXISTS `blog`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
`excerpt` VARCHAR(500) NULL,
  `user_id` INT NOT NULL,
  `creation_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `last_update` DATETIME ON UPDATE CURRENT_TIMESTAMP,
  `image` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_artical_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_artical_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `blog`.`user` (`id`)
)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `blog`.`paragraph`;
CREATE TABLE IF NOT EXISTS `blog`.`paragraph` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` TEXT(65000) NULL,
  `image` MEDIUMBLOB NULL,
  `imageCaption` VARCHAR(255) NULL,
  `article_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_paragraph_article1_idx` (`article_id` ASC),
  CONSTRAINT `fk_paragraph_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `blog`.`article` (`id`)
    )
<<<<<<< HEAD
ENGINE = InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `blog`.`comment`;
CREATE TABLE `blog`.`comment` (
  `id` INT NOT NULL,
  `context` VARCHAR(1000) NOT NULL,
  `user_id` INT NULL,
  `creation_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `reply_to_comment_id` INT NULL,
  `deleted` TINYINT(1) DEFAULT 0,
  `reply_count` INT DEFAULT 0,
  `article_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_comment_article1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_comment_reply` (`reply_to_comment_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `blog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_comment_reply`
    FOREIGN KEY (`reply_to_comment_id`)
    REFERENCES `blog`.`comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `blog`.`article` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
=======
ENGINE = InnoDB DEFAULT CHARSET=utf8;
>>>>>>> refs/remotes/origin/master
