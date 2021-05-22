create table if not exists type(
id serial primary key,
name varchar(255)
);
create table if not exists product(
id serial primary key,
name varchar(255),
expired_date date,
price float,
type_id int references type(id)
);