create sequence hibernate_sequence start with 1 increment by 1;
create table address (id bigint not null, street varchar(255) not null, person_id bigint not null, primary key (id));
create table phone (id bigint not null, phone_number varchar(255) not null, user_id bigint not null, primary key (id));
create table users (id bigint not null, login varchar(255), name varchar(255), password varchar(255), primary key (id));
alter table address add constraint FKncldppt0ukeeq7x2p4olbvhei foreign key (person_id) references users;
alter table phone add constraint FKoi5lexwwtw7lmkmiqn8yc3i9r foreign key (user_id) references users;