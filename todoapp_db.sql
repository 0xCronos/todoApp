drop database todoappdb;
drop user todoapp;
create user todoapp with password 'mysecretpassword';
create database todoappdb with template=template0 owner=todoapp;
\connect todoappdb;
alter default privileges grant all on tables to todoapp;

create table users (
    id UUID default gen_random_uuid() primary key not null,
    username varchar(50) not null,
    email varchar(255) not null,
    password varchar(1024) not null
);

create table todolists (
    id UUID default gen_random_uuid() primary key not null,
    title varchar(50) not null,
    user_id UUID not null
);

create table tasks (
    id UUID default gen_random_uuid() primary key not null,
    title varchar(50) not null,
    description varchar(255),
    todolist_id UUID not null
);

alter table todolists add constraint todolists_users_fk
foreign key (user_id) references users(id);

alter table tasks add constraint tasks_todolists_fk
foreign key (todolist_id) references todolists(id);