create table car_body(
id serial primary key,
number_body int
);
create table engine(
id serial primary key,
number_engine int
);
create table transmission(
id serial primary key,
name varchar(255)
);
create table car(
id serial primary key,
name varchar(255),
car_body_id int references car_body(id) not null,
engine_id int references engine(id) not null,
transmission_id int references transmission(id) not null
);
alter table car_body alter column number_body type bigint;