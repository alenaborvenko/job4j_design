create table if not exists owner(
id serial primary key,
name_owner varchar(255),
surname varchar(255),
age int,
birthaday date
);
create table if not exists pet(
id serial primary key,
name_pet varchar(255),
age int,
birthaday date,
id_owner int references owner(id)
);
alter table owner rename column birthaday to birthday;
alter table pet rename column birthaday to birthday;