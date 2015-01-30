DROP TABLE UserRoles;
DROP TABLE Roles;
DROP TABLE Users;

CREATE TABLE `Roles` (
  `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

# CREATE TABLE `Users` (
#   `id`                     BIGINT(20)   NOT NULL AUTO_INCREMENT,
#   `account_expired`        BIT(1)       NOT NULL,
#   `account_locked`         BIT(1)       NOT NULL,
#   `creation_date`          DATETIME     NOT NULL,
#   `is_enabled`             BIT(1)       NOT NULL,
#   `firstname`              VARCHAR(255) NOT NULL,
#   `lastname`               VARCHAR(255) NOT NULL,
#   `last_modification_date` DATETIME     NOT NULL,
#   `password`               VARCHAR(255) NOT NULL,
#   `password_expired`       BIT(1)       NOT NULL,
#   `username`               VARCHAR(255) NOT NULL,
#   PRIMARY KEY (`id`)
# )
#   ENGINE = InnoDB;

CREATE TABLE `Users` (
  `id`                     BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `account_expired`        BIT(1)       NULL,
  `account_locked`         BIT(1)       NULL,
  `creation_date`          DATETIME     NULL,
  `is_enabled`             BIT(1)       NULL,
  `firstname`              VARCHAR(255) NULL,
  `lastname`               VARCHAR(255) NULL,
  `last_modification_date` DATETIME     NULL,
  `password`               VARCHAR(255) NULL,
  `password_expired`       BIT(1)       NULL,
  `username`               VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

CREATE TABLE `UserRoles` (
  `role_id` BIGINT(20) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`role_id`, `user_id`)
)
  ENGINE = InnoDB;
ALTER TABLE UserRoles ADD CONSTRAINT `FK_USER_ROLES_USER` FOREIGN KEY (user_id) REFERENCES Users (id);
ALTER TABLE UserRoles ADD CONSTRAINT `FK_USER_ROLES_ROLE` FOREIGN KEY (role_id) REFERENCES Roles (id);



INSERT INTO Roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO Roles VALUES (2, 'ROLE_USER');
INSERT INTO Users VALUES (1,0 ,0, '2013-03-14 14:27:57',1, 'admin', 'admin', '2013-03-14 14:27:57', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',0, 'admin');
INSERT INTO Users VALUES (2,0 ,0, '2013-03-14 14:27:58',1, 'user', 'user', '2013-03-14 14:27:58', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb',0, 'user');
INSERT INTO UserRoles VALUES (1, 1);
INSERT INTO UserRoles VALUES (2, 2);