create table departments(
id serial primary key,
name varchar(255)
);
create table employee(
id serial primary key,
name varchar(255),
departments_id int references departments(id)
);

create table teens(
id serial primary key,
name varchar(255),
gender boolean
);