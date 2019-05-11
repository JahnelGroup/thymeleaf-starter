create table `preferences` (
  id bigint auto_increment primary key,
  name varchar(50) not null,
  description varchar(255) null,
  value varchar(255) not null,
  user varchar(50) not null,
  created_by varchar(50) not null,
  created_datetime timestamp(2) not null,
  last_modified_by varchar(50) not null,
  last_modified_datetime timestamp(2) not null,
  version smallint not null,
  constraint FK_User
  foreign key (user) references users(username)
);