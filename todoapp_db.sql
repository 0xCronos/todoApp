drop database todoappdb;
drop user todoapp;
create user todoapp with password 'mysecretpassword';
create database todoappdb with template=template0 owner=todoapp;
\connect todoappdb;
alter default privileges grant all on tables to todoapp;
alter default privileges grant all on sequences to todoapp;
