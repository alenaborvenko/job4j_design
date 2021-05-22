
insert into type (name) values ('bread');
insert into type (name) values ('cheese');
insert into type (name) values ('milk');
insert into type (name) values ('meat');
insert into type (name) values ('chocolate');

insert into product (name, type_id, expired_date, price) values ('white bread', 1, date '2021-05-22', 10);
insert into product (name, type_id, expired_date, price) values ('grey bread', 1, date '2021-05-22', 20);
insert into product (name, type_id, expired_date, price) values ('black bread', 1, date '2021-05-22', 30);

insert into product (name, type_id, expired_date, price) values ('junior cheese', 2, date '2021-09-02', 200);
insert into product (name, type_id, expired_date, price) values ('middle cheese', 2, date '2021-08-03', 300);
insert into product (name, type_id, expired_date, price) values ('senior cheese', 2, date '2021-07-04', 400);

insert into product (name, type_id, expired_date, price) values ('junior milk', 3, date '2021-05-30', 40);
insert into product (name, type_id, expired_date, price) values ('middle milk', 3, date '2021-05-30', 50);
insert into product (name, type_id, expired_date, price) values ('senior milk', 3, date '2021-05-28', 60);
insert into product (name, type_id, expired_date, price) values ('icecream', 3, date '2022-04-04', 60);

insert into product (name, type_id, expired_date, price) values ('pork', 4, date '2021-06-02', 40);
insert into product (name, type_id, expired_date, price) values ('beef', 4, date '2021-06-03', 60);
insert into product (name, type_id, expired_date, price) values ('chiken', 4, date '2021-06-04', 100);

insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('milk chocolate', 5, date '2022-03-03', 100);
insert into product (name, type_id, expired_date, price) values ('dark chocolate ', 5, date '2022-04-04', 200);

select p.name from product p 
join
type t
on p.type_id = t.id and t.name like '%cheese%';

select p.name from product p where p."name" like '%icecream%';

select p.name from product p where current_date + interval '1 month' >= p.expired_date; 

select p.name, max(p.price) from product p 
group by p.name
order by max(p.price) desc
limit 1;

select t.name, count(p.name) from type t
join
product p 
on t.id = p.type_id
group by t.name;

select p.name from product p 
join
type t
on t.id = p.type_id
where t.name = 'cheese' or t.name = 'milk';

insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);
insert into product (name, type_id, expired_date, price) values ('white chocolate', 5, date '2022-02-02', 60);

select t.name from "type" t 
join
product p 
on t.id = p.type_id 
group by t."name" 
having count(p) < 10; 

select p.name, t.name from product p 
join
"type" t 
on p.type_id = t.id
group by p."name", t."name" 
order by p."name", t."name" ASC;
