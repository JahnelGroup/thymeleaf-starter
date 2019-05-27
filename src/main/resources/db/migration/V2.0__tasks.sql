create table `task_lists` (
  id bigint auto_increment primary key,
  title varchar(255) not null,
  created_by varchar(50) not null,
  created_datetime timestamp(2) not null,
  last_modified_by varchar(50) not null,
  last_modified_datetime timestamp(2) not null,
  version smallint not null
);

create table `tasks` (
  id bigint auto_increment primary key,
  description varchar(255) not null,
  task_list_id bigint not null,
  completed boolean not null,
  created_by varchar(50) not null,
  created_datetime timestamp(2) not null,
  last_modified_by varchar(50) not null,
  last_modified_datetime timestamp(2) not null,
  version smallint not null,
  constraint fk_task_task_lists foreign key(task_list_id) references task_lists(id)
);

create table `shared_task_lists` (
  username varchar(50),
  task_list_id bigint,
  primary key (username, task_list_id),
  constraint fk_users_username foreign key(username) references users(username),
  constraint fk_task_lists_task_list_id foreign key(task_list_id) references task_lists(id)
);