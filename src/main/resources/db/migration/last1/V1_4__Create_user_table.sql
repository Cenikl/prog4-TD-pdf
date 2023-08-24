create table if not exists Users(
    id serial constraint user_id primary key,
    username varchar not null constraint username_unique unique,
    password varchar not null
)