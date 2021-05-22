insert into people(name) values ('Аня');
insert into people(name) values ('Ваня');
insert into people(name) values ('Боря');

insert into devices(name) values ('smartphone');
insert into devices(name) values ('tv');
insert into devices(name) values ('smartwatch');
select * from devices;
select * from people;

insert into devices_people(device_id, people_id, price) values (1, 1, 50909.99);
insert into devices_people(device_id, people_id, price) values (2, 1, 20909.99);
insert into devices_people(device_id, people_id, price) values (3, 1, 10909.99);

insert into devices_people(device_id, people_id, price) values (1, 2, 25900.00);
insert into devices_people(device_id, people_id, price) values (2, 2, 25909.99);
insert into devices_people(device_id, people_id, price) values (3, 2, 7800.00);

insert into devices_people(device_id, people_id, price) values (1, 3, 8000.00);
insert into devices_people(device_id, people_id, price) values (2, 3, 15345);
insert into devices_people(device_id, people_id, price) values (3, 3, 20980);
select * from devices_people;

select d.name, avg(dp.price) from devices_people dp 
join devices d 
on dp.device_id = d.id 
group by d.name order by d.name;

select p.name, avg(dp.price) from people p 
join devices_people dp 
on p.id = dp.people_id 
group by p.name;

update devices_people set price = 2500 where people_id = 3;
select p.name, avg(dp.price) from people p 
join devices_people dp 
on p.id = dp.people_id 
group by p."name" 
having avg(dp.price) > 5000;