create table if not exists "phone"(
    id serial primary key,
    phoneNumber varchar(10) not null,
    phoneEmployee bigint
        constraint fk_employee_id references employee(id),
    phoneEnterprise bigint
        constraint fk_enterprise_id references enterprise(id)
);
alter table "phone" add constraint phone_check
check ( (phoneEmployee is null and phoneEnterprise is not null) or
        (phoneEmployee is not null and phoneEnterprise is null)
    );


