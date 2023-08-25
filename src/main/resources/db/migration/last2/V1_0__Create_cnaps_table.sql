create type sex as enum ('M','F');
create type csp as enum ('M1','M2','OS1','OS2','OS3','OP1');
create table if not exists "cnaps"
(
    id serial constraint employee_id primary key ,
    firstName varchar not null,
    lastName varchar not null,
    birthDate date not null,
    sex sex not null,
    csp csp not null,
    matricule varchar not null constraint employee_matricule_unique unique,
    address varchar not null,
    emailPro varchar not null constraint email_pro_unique unique,
    emailPerso varchar not null constraint email_perso_unique unique,
    "role" varchar not null,
    child integer,
    employementDate date not null,
    departureDate date,
    cnaps varchar not null,
    cin varchar not null,
    emplImg oid,
    endToEndId varchar not null
);