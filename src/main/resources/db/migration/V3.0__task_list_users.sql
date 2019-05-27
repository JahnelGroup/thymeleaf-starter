create table `task_list_users` (
  id bigint auto_increment primary key,
  task_list_id bigint not null,
  username varchar(50) not null,
  created_by varchar(50) not null,
  created_datetime timestamp(2) not null,
  last_modified_by varchar(50) not null,
  last_modified_datetime timestamp(2) not null,
  version smallint not null,
  constraint fk_task_list foreign key(task_list_id) references tasks(id)
);

