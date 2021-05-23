insert into car_body (number_body) values (1234565789);
insert into car_body (number_body) values (1357911121);
insert into car_body (number_body) values (2468101214);
select * from car_body cb ;

insert into engine(number_engine) values (12345678);
insert into engine(number_engine) values (24681011);
insert into engine(number_engine) values (35791113);
select * from engine e;

insert into transmission (name) values ('auto');
insert into transmission (name) values ('manual');
select * from transmission t ;

insert into car (name, car_body_id, engine_id, transmission_id)
values ('renault logan', 1, 1, 1);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('renault logan', 1, 1, 2);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('renault logan', 1, 2, 1);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('renault logan', 1, 2, 2);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('renault logan', 2, 2, 1);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('renault logan', 2, 1, 2);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('renault logan', 2, 2, 2);

insert into car (name, car_body_id, engine_id, transmission_id)
values ('bmv', 1, 1, 1);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('bmv', 1, 1, 2);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('bmv', 1, 2, 1);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('bmv', 1, 2, 2);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('bmv', 2, 2, 1);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('bmv', 2, 1, 2);
insert into car (name, car_body_id, engine_id, transmission_id)
values ('bmv', 2, 2, 2);
select * from car;

select c.name as name, cb.number_body as number_body, e.number_engine as number_engine, t.name as transmission
from car c 
join car_body cb on c.car_body_id = cb.id
join engine e on c.engine_id = e.id
join transmission t on c.transmission_id  = t.id
order by c."name", number_body, number_engine, transmission  ;

select cb.number_body from car_body cb left join car c on c.car_body_id = cb.id where c.car_body_id is null;

select e.number_engine from engine e left join car c on c.engine_id = e.id where c.engine_id is null;

select t."name" from transmission t left join car c on c.transmission_id = t.id where c.transmission_id is null;