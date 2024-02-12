
create table if not exists products
(
    id   int
        primary key GENERATED BY DEFAULT AS IDENTITY,
    name varchar(255)
);

create table if not exists roles
(
    id   int
        primary key GENERATED BY DEFAULT AS IDENTITY,
    name varchar(255)
);

create table if not exists users
(
    id       bigint not null
        primary key GENERATED BY DEFAULT AS IDENTITY,
    email    varchar(255),
    password varchar(255),
    username varchar(255)
);

create table if not exists events
(
    id          bigint
        primary key GENERATED BY DEFAULT AS IDENTITY,
    action      varchar(255) not null,
    action_date timestamp(6),
    user_id     bigint
        constraint fkat8p3s7yjcp57lny4udqvqncq
            references users
);


create index idx_owner
    on events (user_id);

create table if not exists user_roles
(
    user_id bigint  not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_id int not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles
);

create index idx_username
    on users (username);


