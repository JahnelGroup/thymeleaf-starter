create table `notifications` (
  id bigint auto_increment primary key,
  recipient varchar(50) not null,
  sender varchar(50) null,
  content varchar(255) not null,
  is_read  tinyint(1) not null,
  created_by varchar(50) not null,
  created_datetime timestamp(2) not null,
  last_modified_by varchar(50) not null,
  last_modified_datetime timestamp(2) not null,
  version smallint not null
);
