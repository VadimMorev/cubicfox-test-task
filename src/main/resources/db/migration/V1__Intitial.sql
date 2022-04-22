create table t_address
(
    id      bigserial
        primary key,
    street  varchar(512) not null,
    suite   varchar(512) not null,
    city    varchar(512) not null,
    zipcode varchar(512) not null,
    geo_lat float8      not null,
    geo_lng float8      not null

);
alter table t_address
    owner to postgres;

create table t_company
(
    id          bigserial
        primary key,
    name        varchar(512) not null,
    catch_phrase varchar(512) not null,
    bs          varchar(512) not null
);
alter table t_company
    owner to postgres;
create table t_user
(
    id           bigserial
        primary key,
    name         varchar(512) not null,
    username     varchar(512) not null,
    email        varchar(512) not null
        constraint idx_email_unq
            unique,
    address_id   bigint       not null
        constraint fk_t_user_address_id
            references t_address,
    phone_number varchar(64),
    website      varchar(64),
    company_id   bigint       not null
        constraint fk_t_user_company_id
            references t_company
);

alter table t_user
    owner to postgres;