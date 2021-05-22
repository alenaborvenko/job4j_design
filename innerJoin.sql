insert into owner(name_owner, surname, age, birthday) values('helen', 'borvenko', 30, '22.02.1991');
insert into owner(name_owner, surname, age, birthday) values('petya', 'petrov', 35, '30.05.1986');
insert into owner(name_owner, surname, age, birthday) values('vasya', 'vasechkin', 20, '31.12.2001');
select * from owner;

insert into pet(name_pet, age, birthday, id_owner) values('klepa', 2, '08.07.2019',
(select id from owner where name_owner = 'helen')
);
insert into pet(name_pet, age, birthday, id_owner) values('cat', 4, '30.05.2017',
(select id from owner where name_owner = 'petya')
);
insert into pet(name_pet, age, birthday, id_owner) values('dog', 7, '17.10.2014',
(select id from owner where name_owner = 'petya')
);
insert into pet(name_pet, age, birthday, id_owner) values('bird', 10, '02.03.2011',
(select id from owner where name_owner = 'vasya')
);
select * from pet;

select name_owner as owner, name_pet as pet from owner join pet on owner.id = pet.id_owner order by owner desc; 
select name_owner as owner, surname, name_pet as pet from owner join pet on owner.id = pet.id_owner order by owner.surname desc;
select owner.birthday as owner_birthday, pet.birthday as pet_birthday from owner join pet on owner.id = pet.id_owner order by owner.birthday asc; 