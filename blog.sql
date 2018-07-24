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
