DROP TABLE purposes;
DROP TABLE user_roles;
DROP TABLE roles;
DROP TABLE users;
DROP TABLE instance_types;
DROP TABLE approved_amis;

CREATE TABLE `roles` (
  `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `users` (
  `id`                     BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `username`               VARCHAR(255) NULL,
  `aws_client_id`          VARCHAR(255) NULL,
  `aws_client_secret`      VARCHAR(1024) NULL,
  `has_aws_configuration`  BIT(1) NOT NULL DEFAULT 0,
  `creation_date`          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modification_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `user_roles` (
  `role_id` BIGINT(20) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`role_id`, `user_id`)
) ENGINE = InnoDB;

ALTER TABLE user_roles ADD CONSTRAINT `FK_USER_ROLES_USER` FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE user_roles ADD CONSTRAINT `FK_USER_ROLES_ROLE` FOREIGN KEY (role_id) REFERENCES roles (id);

CREATE TABLE `purposes` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `instance_types` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `aws_instance_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `approved_amis` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `aws_instance_id` varchar(255) NOT NULL,
  `is_workshop` BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `regular_instances` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `owner_id` BIGINT(20) NOT NULL,
  `instance_type_id` BIGINT(20) NOT NULL,
  `approved_ami_id` BIGINT(20) NOT NULL,
  `purpose_id` BIGINT(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `state` varchar(100) NOT NULL,
  `domain` varchar(256) NULL,
  `sfdc_url` varchar(256) NULL,
  `has_domain` BIT NOT NULL DEFAULT 0,
  `has_eip` BIT NOT NULL DEFAULT 0,
  `date_start` DATETIME NOT NULL,
  `date_stop` DATETIME NOT NULL,
  `date_terminate` DATETIME,
  `date_creation` DATETIME NOT NULL,
  `date_last_modification` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;


delete from user_roles;
delete from roles;
delete from users;
delete from purposes;
delete from instance_types;
delete from approved_instances;

insert into roles values (1, 'ROLE_ADMIN');
insert into roles values (2, 'ROLE_USER');

insert into purposes values (1, 'Demo');
insert into purposes values (2, 'PoC');
insert into purposes values (3, 'Shared');
insert into purposes values (4, 'Other');

insert into instance_types values (1, 'Small', 't2.mall');
insert into instance_types values (2, 'Medium', 't2.medium');
insert into instance_types values (3, 'Large', 'm3.large');
insert into instance_types values (4, 'Extra Large', 'm3.xlarge');

insert into approved_amis values (1, 'Mulesoft Solutions Workshop R 4.3.0 - Beta 1', 'ami-2ea4f546', true);
insert into approved_amis values (2, 'Mulesoft Solutions Workshop R 4.2.2', 'ami-44593e2c', false);
insert into approved_amis values (3, 'MuleSoft Linux CentOS 6.4 Base Image', 'ami-ee532d87', false);
insert into approved_amis values (4, 'Demo: Order Entry Showcase Environment', 'ami-5cfe4634', false );
