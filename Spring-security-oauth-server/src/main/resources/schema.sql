create table  if not exists users (id IDENTITY not null ,username varchar(45), password varchar(45),enabled INT ,primary key(id));

create table if not exists authorities (id IDENTITY not null ,username varchar(45),authority varchar(45), primary key(id));

INSERT INTO USERS (username,password,enabled) values('sumit','12345',1);
INSERT INTO USERS (username,password,enabled) values('amit','12345',1);


INSERT INTO authorities(username,authority) values ('sumit','read');
INSERT INTO authorities(username,authority) values ('amit','read');

create table  if not exists oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

create  table  if not exists oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);