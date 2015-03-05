DROP TABLE user_roles;
DROP TABLE roles;
DROP TABLE users;

CREATE TABLE `roles` (
  `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

CREATE TABLE `users` (
  `id`                     BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `username`               VARCHAR(255) NULL,
  `password`               VARCHAR(255) NULL,
  `aws_client_id`          VARCHAR(255) NULL,
  `aws_client_secret`      VARCHAR(1024) NULL,
  `has_aws_configuration`  BIT(1) NOT NULL DEFAULT 0,
  `ldap_user_id`           VARCHAR(1024) NULL,
  `creation_date`          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modification_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

CREATE TABLE `user_roles` (
  `role_id` BIGINT(20) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`role_id`, `user_id`)
)
  ENGINE = InnoDB;
ALTER TABLE user_roles ADD CONSTRAINT `FK_USER_ROLES_USER` FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE user_roles ADD CONSTRAINT `FK_USER_ROLES_ROLE` FOREIGN KEY (role_id) REFERENCES roles (id);
delete from user_roles;
delete from roles;
delete from users;

insert into roles values (1, 'ROLE_ADMIN');
insert into roles values (2, 'ROLE_USER');

INSERT INTO users (`id`, `aws_client_id`, `aws_client_secret`, `has_aws_configuration`, `ldap_user_id`, `password`, `username`)
VALUES (1, 1, 1, 0, 1, 'admin', 'admin');

insert into user_roles (role_id, user_id) values (1,1);
insert into user_roles (role_id, user_id) values (2,1);

