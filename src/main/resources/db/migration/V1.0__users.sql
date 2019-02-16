-- https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema

create table `users` (
  username varchar(50) not null primary key,
  password varchar(60) not null,
  email varchar(255) not null,
  first_name varchar(120) not null,
  last_name varchar(120) not null,
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

insert into `user_groups` (id, group_name) values
  (1, 'Admin'),
  (2, 'User');

insert into `user_group_authorities` (group_id, authority) values
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER');

insert into `users` (username, password, email, first_name, last_name, enabled) values
  ('steven', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'szgaljic@jahnelgroup.com', 'Steven', 'Zgaljic', true),
  ('darrin', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'djahnel@jahnelgroup.com', 'Darrin', 'Jahnel', true),
  ('jason', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'jasonjahnel@jahnelgroup.com', 'Jason', 'Jahnel', true);

insert into `user_group_members` (username, group_id) values
  ('steven', 1),
  ('steven', 2),
  ('darrin', 2),
  ('jason',  2);