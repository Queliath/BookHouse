CREATE DATABASE `online_libary_kursach`;
USE `online_libary_kursach`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `preview` text NOT NULL,
  `number_of_pages` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `file_name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `year` int(11) NOT NULL,
  `date_of_publicaion` date NOT NULL,
  `section_id` int(11) NOT NULL,
  `img` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `date` date NOT NULL,
  `comment_user_id` int(11) NOT NULL,
  `comment_book_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(20) NOT NULL,
  `security_number` varchar(10) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `second_name` varchar(30) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` int(11) NOT NULL,
  `rating_user_id` int(11) NOT NULL,
  `rating_book_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `img` varchar(55) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`section_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `second_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

ALTER TABLE `book`
ADD KEY `section_id_idx` (`section_id`), 
ADD CONSTRAINT `section_id` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `comment`
ADD KEY `comment_user_id_idx` (`comment_user_id`),
ADD KEY `comment_book_id_idx` (`comment_book_id`),
ADD CONSTRAINT `comment_book_id` FOREIGN KEY (`comment_book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `comment_user_id` FOREIGN KEY (`comment_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `purchase`
ADD KEY `user_id_idx` (`user_id`),
ADD KEY `book_id_idx` (`book_id`),
ADD CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `rating`
ADD KEY `rating_user_id_idx` (`rating_user_id`),
ADD KEY `rating_book_id_idx` (`rating_book_id`),
ADD CONSTRAINT `rating_book_id` FOREIGN KEY (`rating_book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `rating_user_id` FOREIGN KEY (`rating_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

