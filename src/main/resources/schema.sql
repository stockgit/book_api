CREATE TABLE `books`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `author`         varchar(255) DEFAULT NULL,
    `published_date` date         DEFAULT NULL,
    `title`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY              `idx_author` (`author`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

