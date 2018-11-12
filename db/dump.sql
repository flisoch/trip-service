create table service_user
(
	id serial not null
		constraint service_users_pk
			primary key,
	username varchar(50) not null,
	email varchar(50),
	hash_password varchar(500),
	name varchar(50),
	working_place varchar(50),
	age integer,
	additional_info varchar(500),
	photo varchar(200) default '/static/pictures/default.png'::character varying,
	address varchar(200),
	lastname varchar(50),
	token varchar(500)
)
;

create unique index service_user_username_uindex
	on service_user (username)
;

create table comment_user
(
	id serial not null
		constraint comment_user_pkey
			primary key,
	commentator_id integer
		constraint comment_user_commentator_id_fkey
			references service_user,
	commentatee_id integer
		constraint comment_user_commentatee_id_fkey
			references service_user,
	text varchar(500),
	datetime timestamp
)
;

create table trip
(
	id serial not null
		constraint trip_pkey
			primary key,
	arrival_point varchar(200),
	departure_point varchar(200),
	datetime timestamp,
	free_seats integer default 0
		constraint free_seats_positive
			check (free_seats >= 0),
	initiator_id integer
		constraint trip_initiator_id_fkey
			references service_user,
	info varchar(200)
)
;

create table comment_trip
(
	id serial not null
		constraint comment_trip_pkey
			primary key,
	trip_id integer
		constraint comment_trip_trip_id_fkey
			references trip,
	commentator_id integer
		constraint comment_trip_commentator_id_fkey
			references service_user,
	text varchar(500),
	datetime timestamp
)
;

create table trip_user_apply
(
	trip_id bigint not null,
	user_id bigint not null,
	id bigserial not null
		constraint trip_user_apply_pk
			primary key
)
;

create unique index trip_user_apply_id_uindex
	on trip_user_apply (id)
;

create unique index trip_user__id_uindex
	on trip_user_apply (trip_id, user_id)
;

create table booked_trip
(
	id bigserial not null
		constraint trip_user_pkey
			primary key,
	trip_id bigint not null,
	user_id bigint not null
)
;

create unique index trip_user_uindex
	on booked_trip (trip_id, user_id)
;

INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (23, 'joshua', 'user3@mail.ru', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', 'anatoly', 'oracle', 21, 'I have a rodinka on my ear', '/static/pictures/default.png', 'pushkina,kolotushkina', 'lastname', 'i%1E%EF%BF%BD%EF%BF%BD%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%126X%EF%BF%BD%0C%5BPF%EF%BF%BD%EF%BF%BD1%EF%BF%BD%EF%BF%BDB%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BDDvA_%12%EF%BF%BD%09%EF%BF%BD%EF%BF%BD%EF%BF%BD%5B%3D%05%EF%BF%BDI%EF%BF%BD%1A%EF%BF%BD%23%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BDG2%EF%BF%BD%EF%BF%BD%EF%BF%BDG%60%7F%26.');
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (19, 'user1           ', 'user14@mail.ru', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', 'name', 'MICROSOFT', 15, 'I have a rodinka on my ear', '/static/pictures/mily.png', 'pushkina,kolotushkina', 'lastname', '%21%3EF%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%0C%EF%BF%BD%EF%BF%BD%1F%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BDZ%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7E%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%12%EF%BF%BD%EF%BF%BD%28%EF%BF%BD%EF%BF%BDq%EF%BF%BD%EF%BF%BDLP%EF%BF%BD%EF%BF%BD%EF%BF%BD%1E%0A%EF%BF%BD%1ENy%07%EF%BF%BD%60%0ES%EF%BF%BDb%3F%EF%BF%BD%28%EF%BF%BD');
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (24, 'tolya', 'tolya@mail.ru', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', null, null, null, '', '/static/pictures/mily.png', null, null, null);
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (27, 'filya', 'asd@mail.ti', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', 'dolya', 'itis', 17, 'I have a rodinka on my ear', '/static/pictures/default.png', 'pushkina,kolotushkina', 'lastname', 'T%23%13%EF%BF%BDm8E%2F%EF%BF%BD%EF%BF%BD%EF%BF%BD%2F%EF%BF%BD%EF%BF%BDb%EF%BF%BD%274%18%26c%EF%BF%BD%EF%BF%BD%EF%BF%BDRz%26%EF%BF%BDx%1F%EF%BF%BD%EF%BF%BD%04%EF%BF%BDb%EF%BF%BD%EF%BF%BD%EF%BF%BDC%60*%EF%BF%BDK7%1B%EF%BF%BD%27%EF%BF%BDsQBx%28%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BDO%29');
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (34, 'kilog', 'kilo@msil', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', null, null, null, null, '/static/pictures/mily.png', null, null, null);
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (33, 'user19', 'fff@mi.tu', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', 'uuduu', 'oracle', 12, 'I have a rodinka on my ear', '/static/pictures/default.png', 'pushkina,kolotushkina', 'lastname', null);
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (32, 'user2', 'user1@mail.di', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', 'Alexey', 'oracle', 17, 'I have a rodinka on my ear', '/static/pictures/default.png', 'pushkina,kolotushkina', 'lastname', '%3FJL%EF%BF%BD2hzF_%0E%29d%EF%BF%BD%EF%BF%BD%EF%BF%BDT%EF%BF%BD%EF%BF%BD%EF%BF%BD%7F+%EF%BF%BD%01l%EF%BF%BDg%EF%BF%BD%1C%EF%BF%BDQ4b%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7Bu%EF%BF%BD1t%EF%BF%BDo%EF%BF%BD%EF%BF%BD%5D%EF%BF%BDf%12%05%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%0F%EF%BF%BD%EF%BF%BDV%EF%BF%BD%10%EF%BF%BD%EF%BF%BD%5C');
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (35, 'ppsss', 'llss@s.tt', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', null, null, null, null, '/static/pictures/mily.png', null, null, null);
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (36, 'user99', 'asd@mail.ti', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', '', '', 10, null, '/static/pictures/default.png', 'Mskva, stroiteley', '', 'M%00%EF%BF%BDY%EF%BF%BD%EF%BF%BD%60%EF%BF%BD%EF%BF%BD%EF%BF%BDu%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD_%EF%BF%BD%EF%BF%BDY%7F%EF%BF%BD%0F%EF%BF%BDd%EF%BF%BD%EF%BF%BD%05%EF%BF%BDy%EF%BF%BDWM%EF%BF%BD%EF%BF%BD%24%EF%BF%BD%7D%01%11%EF%BF%BDJ%23k%1C%EF%BF%BDF%EF%BF%BD%16%25%21%EF%BF%BD%EF%BF%BD9Y%02%3D4m%EF%BF%BD%EF%BF%BD%2C.%EF%BF%BD%EF%BF%BD');
INSERT INTO public.service_user (id, username, email, hash_password, name, working_place, age, additional_info, photo, address, lastname, token) VALUES (22, 'andre', 'user2@mail.ru', '%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1Cv%5DE%12%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1A%06%EF%BF%BD%EF%BF%BD%EF%BF%BDA%0B%2B%5D%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%01%EF%BF%BD%EF%BF%BD%24r%EF%BF%BD%EF%BF%BDC%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%7D%00%EF%BF%BD%EF%BF%BD%0F%3D%06%EF%BF%BD%22%EF%BF%BD%EF%BF%BD%EF%BF%BD%13%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%03%10%EF%BF%BDz%EF%BF%BD%EF%BF%BD%EF%BF%BD', 'android', 'itis', 18, null, '/static/pictures/mily.png', 'pushkina,kolotushkina', 'lastname', '%EF%BF%BDV%EF%BF%BDq%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%1F%EF%BF%BDb%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD7%EF%BF%BD%EF%BF%BD%EF%BF%BDJ%EF%BF%BDf%EF%BF%BDT2%EF%BF%BD%0E%08%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%2F%08%5E%3E%26L7%7E%EF%BF%BDprD%EF%BF%BDrzJ%EF%BF%BD%EF%BF%BDp%EF%BF%BD%0Bw%EF%BF%BD%EF%BF%BDr%0E+7%EF%BF%BD%EF%BF%BD%EF%BF%BD');


INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (9, 'das', 'sdas', '2000-01-01 23:00:00.000000', 2, 23, 'asda');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (10, 'dfsdf', 'fsdfs', '1970-01-01 23:00:00.000000', 3, 23, 'sdfsdss');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (11, 'tashkent', 'kazan', '2020-01-01 23:00:00.000000', 4, 27, 'trip to tashkent on my back');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (12, 'izhevsk', 'kazan', '2021-01-01 23:00:00.000000', 1, 19, 'izhevskoy trip');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (14, 'strana', 'gorod', '1970-01-01 23:00:00.000000', 2, 32, 'sssssssssssssssssssssssss');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (8, 'kazan', 'zelenodolsak', '2020-01-01 23:00:00.000000', 1, 23, 'have a free seat on my ferrari,
search for a cool guy to commute 
from zelenodoslak to kazan ');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (15, 'aaa', 'bbb', '1970-01-01 23:00:00.000000', 10, 22, 'idjdjdjd');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (19, 'asd', 'asd', '1970-01-01 23:00:00.000000', 2, 36, null);
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (17, 'kazan', 'kazan', '2020-01-01 20:00:00.000000', 1, 22, '');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (20, 'sadasd', 'asda', '2020-01-01 23:00:00.000000', 3, 36, null);
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (13, 'gorod', 'strana', '1970-01-01 23:00:00.000000', 3, 32, 'gorodskaya stana ');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (4, 'kazan', 'kazan', '1990-01-01 23:00:00.000000', 2, 23, 'from kzan to kzan, WOW');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (2, 'kazan', 'zelenodolsak', '2018-12-01 09:05:00.000000', 1, 22, 'k Dimke v gosti!');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (1, 'zelenodolsak', 'kazan', '2018-12-19 11:57:52.047000', 3, 19, 'thihs ihs inhformahtion');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (3, 'kazan', 'ufa', '2018-10-23 12:05:58.020000', 3, 19, 'za medom');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (7, 'kazan', 'zelenodolsak', '2019-01-01 23:00:00.000000', 40, 23, 'za molokom');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (6, 'kazan', 'zelenodolsak', '2019-01-01 23:00:00.000000', 4, 23, 'za molokom');
INSERT INTO public.trip (id, arrival_point, departure_point, datetime, free_seats, initiator_id, info) VALUES (5, 'kazan', 'zelenodolsak', '2019-01-01 23:00:00.000000', 4, 23, 'za molokom');


INSERT INTO public.comment_trip (id, trip_id, commentator_id, text, datetime) VALUES (1, 3, 22, 'the worst trip in my life', '2018-10-23 13:59:42.865000');
INSERT INTO public.comment_trip (id, trip_id, commentator_id, text, datetime) VALUES (93, 1, 23, 'it was my best trip', '2018-10-26 01:17:43.804000');
INSERT INTO public.comment_trip (id, trip_id, commentator_id, text, datetime) VALUES (92, 1, 19, 'very good!', '2018-10-24 23:46:18.249000');
INSERT INTO public.comment_trip (id, trip_id, commentator_id, text, datetime) VALUES (122, 1, 22, 'yes, so much fun!guys you are the best!', '2018-10-26 06:42:03.767000');
INSERT INTO public.comment_trip (id, trip_id, commentator_id, text, datetime) VALUES (90, 2, 22, 'not bad', '2018-10-24 18:20:13.229000');


INSERT INTO public.comment_user (id, commentator_id, commentatee_id, text, datetime) VALUES (1, 27, 22, 'Krutoy pacan', '2018-11-11 11:33:44.400000');
INSERT INTO public.comment_user (id, commentator_id, commentatee_id, text, datetime) VALUES (2, 19, 22, 'Nice poput4ik!', '2018-11-11 11:45:36.477000');
INSERT INTO public.comment_user (id, commentator_id, commentatee_id, text, datetime) VALUES (9, 22, 23, 'REALLY COOL DRIVER', '2018-11-11 16:16:08.574000');
INSERT INTO public.comment_user (id, commentator_id, commentatee_id, text, datetime) VALUES (18, 22, 27, 'I''M FIRST
', '2018-11-12 05:19:48.758000');


INSERT INTO public.booked_trip (id, trip_id, user_id) VALUES (11, 2, 33);
INSERT INTO public.booked_trip (id, trip_id, user_id) VALUES (12, 2, 22);
INSERT INTO public.booked_trip (id, trip_id, user_id) VALUES (13, 16, 27);
INSERT INTO public.booked_trip (id, trip_id, user_id) VALUES (14, 17, 36);
INSERT INTO public.booked_trip (id, trip_id, user_id) VALUES (15, 20, 22);


INSERT INTO public.trip_user_apply (trip_id, user_id, id) VALUES (12, 32, 31);
INSERT INTO public.trip_user_apply (trip_id, user_id, id) VALUES (8, 35, 33);
INSERT INTO public.trip_user_apply (trip_id, user_id, id) VALUES (3, 22, 78);
INSERT INTO public.trip_user_apply (trip_id, user_id, id) VALUES (4, 22, 79);