insert ignore into `user_groups` (id, group_name) values
  (1, 'Admin'),
  (2, 'User');

insert ignore into `user_group_authorities` (group_id, authority) values
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER');

insert ignore into `users` (username, password, email, first_name, last_name, enabled) values
  ('steven', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'szgaljic@jahnelgroup.com', 'Steven', 'Zgaljic', true),
  ('darrin', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'djahnel@jahnelgroup.com', 'Darrin', 'Jahnel', true),
  ('jason', '$2a$12$AcPJ5D0I1XXvSjDWgZGO4OJ9x33VRxPy/BqtNLe.pOaUmZpMD2EK.', 'jasonjahnel@jahnelgroup.com', 'Jason', 'Jahnel', true);

insert ignore into `user_group_members` (id, username, group_id) values
  (1, 'steven', 1),
  (2, 'steven', 2),
  (3, 'darrin', 2),
  (4, 'jason',  2);