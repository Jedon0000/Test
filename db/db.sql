create database userlogin;

use  userlogin;

create table user(
	id int primary key auto_increment,
	username varchar(20) not null,
	password varchar(32) not null,
	type varchar(20) not null
);

insert into user values(null, 'aaa', 'aaa', 'admin');
insert into user values(null, 'bbb', 'bbb', 'user');
insert into user values(null, 'ccc', 'ccc', 'admin');
insert into user values(null, 'ddd', 'ddd', 'user');

