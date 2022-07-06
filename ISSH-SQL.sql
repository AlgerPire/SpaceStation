create database issh;
use issh;
create table info(
id int auto_increment not null,
primary key(id),
city varchar(255),
county varchar(255),
division varchar(255),
locality varchar(255),
continent varchar(255),
ora time
);