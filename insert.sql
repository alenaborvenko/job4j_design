insert into roles(name_role) values ('admin');
insert into roles(name_role) values ('user');

insert into users values(1, 'vasya', 'vasechkin', '20', (select id from roles where name_role = 'user'));
insert into users values(2, 'petya', 'vasechkin', '40', (select id from roles where name_role = 'admin'));

insert into category values(1, 'first');
insert into category values(2, 'second');

insert into state values(1, 'Russia');
insert into state values(2, 'USA');

insert into item values(1, 'first item', 
(select id from users where name_user = 'vasya'),
(select id from category where name_category = 'first'),
(select id from state where name_state = 'Russia')
);

insert into item values(2, 'second item', 
(select id from users where name_user = 'petya'),
(select id from category where name_category = 'second'),
(select id from state where name_state = 'USA')
);

select * from item;