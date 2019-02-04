-- https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema

create table `users` (
  username varchar(50) not null primary key,
  password varchar(60) not null,
  enabled boolean not null
);

create table `authorities` (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table `user_groups` (
  id bigint auto_increment primary key,
  group_name varchar(50) not null
);

create table `user_group_authorities` (
  group_id bigint not null,
  authority varchar(50) not null,
  constraint fk_group_authorities_group foreign key(group_id) references user_groups(id)
);

create table `user_group_members` (
  id bigint auto_increment primary key,
  username varchar(50) not null,
  group_id bigint not null,
  constraint fk_group_members_group foreign key(group_id) references user_groups(id)
);

insert into `users` (username, password, enabled) values
  ('admin', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', true),
  ('user', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', true);

insert into `authorities` (username, authority) values
  ('admin', 'ROLE_ADMIN'),
  ('admin', 'ROLE_USER'),
  ('user', 'ROLE_USER');