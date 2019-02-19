insert ignore into `users` (username, password, email, first_name, last_name, enabled) values
  ('steven', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'szgaljic@jahnelgroup.com', 'Steven', 'Zgaljic', true),
  ('darrin', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'djahnel@jahnelgroup.com', 'Darrin', 'Jahnel', true),
  ('jason', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'jasonjahnel@jahnelgroup.com', 'Jason', 'Jahnel', true);

insert ignore into `user_groups` (id, group_name, created_by, created_datetime, last_modified_by, last_modified_datetime, version) values
  (1, 'System', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 0 ),
  (2, 'Admin' , 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 0 ),
  (3, 'User'  , 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 0 );

insert ignore into `user_group_authorities` (group_id, authority) values
  (1, 'ROLE_SYSTEM'),
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_ADMIN'),
  (3, 'ROLE_USER');

insert ignore into `user_group_members` (id, username, group_id, created_by, created_datetime, last_modified_by, last_modified_datetime, version) values
  (1, 'system', 2, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 0 ),
  (2, 'steven', 2, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 0 ),
  (3, 'steven', 3, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 0 ),
  (4, 'darrin', 3, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 0 ),
  (5, 'jason',  3, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 0 );

insert ignore into `task_lists` (id, title, created_by, created_datetime, last_modified_by, last_modified_datetime, version) values
  (1, 'Shopping list', 'steven', CURRENT_TIMESTAMP, 'steven', CURRENT_TIMESTAMP, 0 );

insert ignore into `tasks` (id, description, completed, task_list_id, created_by, created_datetime, last_modified_by, last_modified_datetime, version) values
  (1, 'Milk', false, 1, 'steven', CURRENT_TIMESTAMP, 'steven', CURRENT_TIMESTAMP, 0 ),
  (2, 'Eggs', false, 1, 'steven', CURRENT_TIMESTAMP, 'steven', CURRENT_TIMESTAMP, 0 ),
  (3, 'Bread', false, 1, 'steven', CURRENT_TIMESTAMP, 'steven', CURRENT_TIMESTAMP, 0 ),
  (4, 'Cereal', true, 1, 'steven', CURRENT_TIMESTAMP, 'steven', CURRENT_TIMESTAMP, 0 ),
  (5, 'Fruits', true, 1, 'steven', CURRENT_TIMESTAMP, 'steven', CURRENT_TIMESTAMP, 0 );