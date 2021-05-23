insert into departments (name) values('sales');
insert into departments (name) values('services');
insert into departments (name) values('accounting');
insert into departments (name) values('development');
select * from departments;

insert into employee (name, departments_id) values('vasya', 1);
insert into employee (name, departments_id) values('petya', 1);
insert into employee (name, departments_id) values('ivan', 1);

insert into employee (name, departments_id) values('mixail', 2);
insert into employee (name, departments_id) values('nikolai', 2);
insert into employee (name, departments_id) values('dima', 2);

insert into employee (name, departments_id) values('nina', 3);
insert into employee (name, departments_id) values('galina', 3);
insert into employee (name, departments_id) values('vera', 3);

insert into employee (name, departments_id) values('nina_null', null);
insert into employee (name, departments_id) values('galina_null', null);
insert into employee (name, departments_id) values('vera_null', null);

select e.name as name, d.name as departments from employee e left join departments d on e.departments_id = d.id; 
select e.name as name, d.name as departments from departments d right join employee e on e.departments_id = d.id;

select e.name as name, d.name as departments from employee e right join departments d on e.departments_id = d.id;

select e.name as name, d.name as departments from employee e full join departments d on e.departments_id = d.id;

select e.name as name, d.name as departments from employee e cross join departments d;

select d.name as departments from departments d left join employee e on d.id = e.departments_id where e.departments_id is null;

insert into teens (name, gender) values('vera', false);
insert into teens (name, gender) values('nadezhda', false);
insert into teens (name, gender) values('lyubov', false);

insert into teens (name, gender) values('gleb', true);
insert into teens (name, gender) values('nikolai', true);
insert into teens (name, gender) values('vasya', true);

select t.name, t.gender, t2."name" , t2.gender from teens t 
cross join teens t2 
where t.gender != t2.gender and t.gender = false and t2.gender = true;
