CREATE TABLE `user` (
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `created_at` varchar(10) DEFAULT NULL,
  `updated_at` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB;

CREATE TABLE `authorities` (
  `username` varchar(256) NOT NULL,
  `authority` varchar(256) NOT NULL,
  PRIMARY KEY (`username`,`authority`)
) ENGINE=InnoDB;

CREATE TABLE `search` (
  `search_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(256) NOT NULL,
  `search_query` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`search_id`),
  KEY `fk_search_user` (`username`),
  CONSTRAINT `fk_search_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

insert into user (username, password, enabled) values ('brian', 'brian', true);

insert into authorities (username, authority) values ('brian', 'ROLE_USER');