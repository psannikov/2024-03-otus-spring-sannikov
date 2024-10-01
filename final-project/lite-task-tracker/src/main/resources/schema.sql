DROP TABLE if exists departments CASCADE;

DROP TABLE if exists priorities CASCADE;

DROP TABLE if exists statuses CASCADE;

DROP TABLE if exists tasks CASCADE;

DROP TABLE if exists users CASCADE;

DROP TABLE if exists works CASCADE;

create table departments (id bigserial,
                          name varchar(255),
                          primary key (id));
create table users (id bigserial,
                    name varchar(255),
                    email varchar(255),
                    login varchar(255),
                    password varchar(255),
                    department_id bigint references departments (id) on delete cascade,
                    primary key (id));
create table priorities (id bigserial,
                         description varchar(255),
                         primary key (id));
create table statuses (id bigserial,
                       description varchar(255),
                       primary key (id));
create table tasks (id bigserial,
                    title varchar(255),
                    description varchar(255),
                    priority_id bigint references priorities (id) on delete cascade,
                    status_id bigint references statuses (id) on delete cascade,
                    parent_id bigint references tasks (id) on delete cascade,
                    start_date date,
                    end_date date,
                    owner_id bigint references users (id) on delete cascade,
                    primary key (id));
create table works (id bigserial,
                    task_id bigint references tasks (id) on delete cascade,
                    description varchar(255),
                    date date,
                    user_id bigint references tasks (id) on delete cascade,
                    primary key (id));

create or replace view tasks$v as
with t1 as
(select c_t.id, c_t.title, c_t.description task_description, p.description priority, s.description status, p_t.id ||':'||p_t.title as parent_task, c_t.start_date, c_t.end_date, o_u.name owner
from tasks c_t
join priorities p on c_t.priority_id = p.id
join statuses s on c_t.status_id = s.id
left join tasks p_t on c_t.parent_id = p_t.id
join users o_u on c_t.owner_id = o_u.id),
t2 as
(select w.id, w.task_id, w.description, w.date, w_u.name, row_number() over (partition by w.task_id order by w.id desc) rn
from works w
left join users w_u on w.user_id = w_u.id
where exists (select 1 from t1
where t1.id=w.task_id)),
t3 as
(select * from t2 where rn =1)
select t1.*,t3.description last_work from t1
left join t3 on t1.id=t3.task_id
order by id, parent_task;