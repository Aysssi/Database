CREATE TABLE `mountains`(
 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 name VARCHAR (45) NOT NULL
 );
 
 CREATE TABLE `peaks`(
 `id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 `name` VARCHAR (45) NOT NULL,
 `mountain_id` INT,
 CONSTRAINT `fk_peaks_mountains` FOREIGN KEY (`mountain_id`)
   REFERENCES `mountains`(`id`)
   ON UPDATE CASCADE
   ON DELETE SET NULL
 );
 
SELECT 	v.`driver_id`, v.`vehicle_type` ,
concat(c.`first_name`, ' ', c.`last_name`) AS `driver_name`
FROM `vehicles` AS `v` JOIN `campers` AS `c`
ON  v.`driver_id` = c.`id`;
 
 
 SELECT `starting_point` AS `route_starting`, 
 `end_point` AS `route_end_point`,`leader_id`,
 concat(c.`first_name`,' ',c.`last_name`)
 FROM `routes` AS r JOIN `campers` AS c
 ON  r.`leader_id` = r.`id`;