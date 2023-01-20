CREATE DATABASE springJdbc;
use springJdbc;

create table student (
rollNo int primary key,
name varchar(20) not null,
marks int
);

insert into student values(101,"Vishal Kumar",97);

select * from student;