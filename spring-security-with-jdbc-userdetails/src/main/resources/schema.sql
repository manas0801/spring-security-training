create table  if not exists users (id IDENTITY not null ,username varchar(45), password varchar(45),enabled INT ,primary key(id));

create table if not exists authorities (id IDENTITY not null ,username varchar(45),authority varchar(45), primary key(id));

INSERT INTO USERS (username,password,enabled) values('sumit','12345',1);
INSERT INTO USERS (username,password,enabled) values('amit','12345',1);


INSERT INTO authorities(username,authority) values ('sumit','read');
INSERT INTO authorities(username,authority) values ('amit','read');