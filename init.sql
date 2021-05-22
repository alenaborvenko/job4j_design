create table if not exists roles(
id serial primary key,
name_role varchar(255)
);
create table if not exists users(
id serial primary key,
name_user varchar(255),
surname text,
age int,
id_roles int references roles(id)
);
create table if not exists rules(
id serial primary key,
name_rules varchar(255)
);
create table if not exists rules_and_role(
id serial primary key,
role_id int references roles(id),
rules_id int references rules(id)
);
create table if not exists category(
id serial primary key,
name_category varchar(255)
);
create table if not exists state(
id serial primary key,
name_state varchar(255)
);
create table if not exists item(
id serial primary key,
name_item varchar(255),
user_id int references users(id),
category_id int references category(id),
state_id int references state(id)
);
create table if not exists comments(
id serial primary key,
txt text,
item_id int references item(id)
);
create table if not exists attachs(
id serial primary key,
name_attachs varchar(255),
path text,
item_id int references item(id)
);