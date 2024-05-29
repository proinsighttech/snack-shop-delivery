create table sequential_code (
    code bigint not null default 0,
    category varchar(255) not null,

    primary key (category),
    unique key uk_category (category)
) engine=InnoDB default charset=utf8;
