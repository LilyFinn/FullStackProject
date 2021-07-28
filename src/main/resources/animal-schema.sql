drop table if exists animal CASCADE;

create table animal
(
id integer PRIMARY KEY AUTO_INCREMENT,
lifeSpan integer not null,
colouring varchar(255), 
depthFound integer not null, 
species varchar(255)
); 
