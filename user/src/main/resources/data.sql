create table company
(
    id              bigint not null
        constraint company_pkey
            primary key,
    image_logo_name varchar(255),
    moto            varchar(255),
    user_id         bigint
        constraint fk1sreugypnov53q59q75q3jonb
            references address
);

alter table company
    owner to sgxkpwevhtqtdb;