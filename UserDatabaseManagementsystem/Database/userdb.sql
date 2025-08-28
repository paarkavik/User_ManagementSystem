create database userdb
use userdb;
create table users (
    id int primary key auto_increment,
    username varchar(50) not null,
    password varchar(50) unique not
    null,
    email  varchar(100) not null
);
select*from users;