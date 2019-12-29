create table user_table (
   id int4 generated by default as identity,
    age int4 not null,
    email varchar(255) not null,
    name varchar(255),
    primary key (id)
);

alter table if exists user_table
   add constraint UK_eamk4l51hm6yqb8xw37i23kb5 unique (email);