create table if not exists "enterprise"(
    id serial
        constraint enterprise_pk primary key,
    name varchar not null,
    description varchar not null,
    slogan varchar not null,
    address varchar not null,
    email varchar not null,
    nif varchar not null,
    stat varchar not null,
    rcs varchar not null,
    logo oid
);
