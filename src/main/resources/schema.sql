drop database if exists todolist;
create database todolist default char set utf8;
use todolist;
create table todos
(
    id int primary key auto_increment,
    description varchar(255) not null,
    done boolean default false not null
);