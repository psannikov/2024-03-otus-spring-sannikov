insert into departments (name) values ('Department_1'), ('Department_1');
insert into users (name, email, login, password, department_id) values
('User1', 'email@example.com','user1','password1',1),
('User2', 'psannikov87@gmail.com','user2','password2',1);
--insert into roles (name, user_id) values('ADMIN',1), ('READER',1), ('READER', 2);
insert into priorities (description) values ('LOW'), ('MIDLE'), ('HIGHT');
insert into statuses (description) values ('TO-DO'), ('IN-PROGRESS'), ('DONE'), ('PAUSED');
insert into tasks (title, description, priority_id, status_id, parent_id, start_date, end_date, owner_id) values
('Task1','Description task 1',1,1,null,now(),null,1),
('Task2','Description task 2',1,1,1,now(),null,1),
('Task3','Description task 3',2,3,1,now(),now(),1),
('Task4','Description task 4',3,2,null,now(),null,1),
('Task5','Description task 5',1,3,5,now(),now(),1),
('Task6','Description task 6',2,2,null,now(),null,2);
insert into works (task_id, description, date, user_id) values
(2,'Description work 1',now(),1),
(2,'Description work 1.1',now(),2),
(3,'Description work 2',now(),2),
(5,'Description work 6',now(),1);