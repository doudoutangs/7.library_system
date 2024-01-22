TRUNCATE TABLE `sp_library`.`b_work_register`;
TRUNCATE TABLE `sp_library`.`b_test_answer`;
TRUNCATE TABLE `sp_library`.`b_class`;
TRUNCATE TABLE `sp_library`.`b_student`;
TRUNCATE TABLE `sp_library`.`b_teacher`;
TRUNCATE TABLE `sp_library`.`sys_user`;
TRUNCATE TABLE `sp_library`.`sys_user_role`;

INSERT INTO sys_user_role (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO sys_user (id, dept_id, post_id, username, password, real_name, email, phone, sex, avatar, birth, address, city, status, is_sys_user, create_time, update_time) VALUES (1, 1, 0, 'admin', '114CNIIUINKMJK72AA1P5807U3', '管理员', '2082233439@qq.com', '15566668888', 1, '1558549283111.png', '2021-11-29', '贵州省贵阳市南明区亭里西路209号万象嘉园18栋', '71', 0, '1', '2021-11-29 14:18:00', null);
