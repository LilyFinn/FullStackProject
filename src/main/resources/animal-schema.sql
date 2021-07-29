drop table if exists animal CASCADE;

create table animal
(
id integer PRIMARY KEY AUTO_INCREMENT,
life_span integer not null,
colouring varchar(255), 
depth_found integer not null, 
species varchar(255)
); 
