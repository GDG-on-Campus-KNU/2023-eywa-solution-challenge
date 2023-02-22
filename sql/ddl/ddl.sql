# drop all tables if exists force
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Animal`;
DROP TABLE IF EXISTS `Dictionary`;
DROP TABLE IF EXISTS `Level`;
DROP TABLE IF EXISTS `Plant`;
DROP TABLE IF EXISTS `Registration`;
DROP TABLE IF EXISTS `Reports`;
DROP TABLE IF EXISTS `Users`;
SET FOREIGN_KEY_CHECKS = 1;

# create tables
CREATE TABLE `Dictionary` (
  `dictionary_id` int NOT NULL AUTO_INCREMENT,
  `korean_name` varchar(255) NULL,
  `english_name` varchar(255) NULL,
  `summary` text NULL,
  `kind` varchar(255) NULL,
  `image` varchar(255) NULL,
  primary key (`dictionary_id`)
);

CREATE TABLE `Plant` (
  `plant_id` int NOT NULL AUTO_INCREMENT,
  `shape_size` text NULL,
  `shape_stem` text NULL,
  `shape_leaf` text NULL,
  `shape_flower_description` text NULL,
  `shape_flower_color` text NULL,
  `shape_fruit` text NULL,
  `ecological_growth_period` text NULL,
  `ecological_bloom_period` text NULL,
  `ecological_habitat_domestic` text NULL,
  `ecological_habitat_overseas` text NULL,
  `introduction_origin` text NULL,
  `introduction_period` text NULL,
  `dictionary_id` int NOT NULL,
  primary key (`plant_id`)
);

CREATE TABLE `Animal` (
  `animal_id` int NOT NULL AUTO_INCREMENT,
  `shape` text NULL,
  `ecological_habitat` text NULL,
  `ecological_lifespan` text NULL,
  `ecological_etc` text NULL,
  `introduction_origin` text NULL,
  `introduction_period` text NULL,
  `introduction_purpose` text NULL,
  `distribution` text NULL,
  `effect_ecosystem` text NULL,
  `effect_entity` text NULL,
  `regulate_past` text NULL,
  `regulate_reason` text NULL,
  `regulate_method` text NULL,
  `designation_domestic` text NULL,
  `designation_overseas` text NULL,
  `designation_organization` text NULL,
  `dictionary_id` int NOT NULL,
  primary key (`animal_id`)
);

CREATE TABLE `Level` (
  `level_id` int NOT NULL AUTO_INCREMENT,
  `level` int NOT NULL,
  `max_exp` int NOT NULL,
  `min_exp` int NOT NULL,
  primary key (`level_id`)
);

CREATE TABLE `Registration` (
  `registration_id` int NOT NULL AUTO_INCREMENT,
  `latitude` decimal(10, 8) NOT NULL,
  `longitude` decimal(11, 8) NOT NULL,
  `time` datetime NOT NULL,
  `dictionary_id` int NOT NULL,
  `user_id` int NOT NULL,
  primary key (`registration_id`)
);

CREATE TABLE `Reports` (
  `report_id` int NOT NULL,
  `latitude` decimal(10, 8) NOT NULL,
  `longitude` decimal(11, 8) NOT NULL,
  `time` datetime NOT NULL,
  `dictionary_id` int NOT NULL,
  `user_id` int NOT NULL,
  primary key (`report_id`)
);

CREATE TABLE `Users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `exp` int NOT NULL,
  `level_id` int NOT NULL,
	primary key (`user_id`)
);

# add constraint
ALTER TABLE `Reports` ADD CONSTRAINT `FK_Dictionary_TO_Reports_1` FOREIGN KEY (
  `dictionary_id`
)
REFERENCES `Dictionary` (
  `dictionary_id`
);

ALTER TABLE `Reports` ADD CONSTRAINT `FK_Users_TO_Reports_1` FOREIGN KEY (
  `user_id`
)
REFERENCES `Users` (
  `user_id`
);

ALTER TABLE `Plant` ADD CONSTRAINT `FK_Dictionary_TO_Plant_1` FOREIGN KEY (
  `dictionary_id`
)
REFERENCES `Dictionary` (
  `dictionary_id`
);

ALTER TABLE `Animal` ADD CONSTRAINT `FK_Dictionary_TO_Animal_1` FOREIGN KEY (
  `dictionary_id`
)
REFERENCES `Dictionary` (
  `dictionary_id`
);

ALTER TABLE `Registration` ADD CONSTRAINT `FK_Dictionary_TO_Registration_1` FOREIGN KEY (
  `dictionary_id`
)
REFERENCES `Dictionary` (
  `dictionary_id`
);

ALTER TABLE `Registration` ADD CONSTRAINT `FK_Users_TO_Registration_1` FOREIGN KEY (
  `user_id`
)
REFERENCES `Users` (
  `user_id`
);

ALTER TABLE `Users` ADD CONSTRAINT `FK_Level_TO_Users_1` FOREIGN KEY (
  `level_id`
)
REFERENCES `Level` (
  `level_id`
);