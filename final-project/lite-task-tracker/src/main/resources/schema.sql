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