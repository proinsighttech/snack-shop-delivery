create table ingredient_stock (
    id bigint not null auto_increment,
    ingredient_id bigint not null,
    stock_quantity smallint(6) not null,

    primary key (id),
    unique key uk_ingredient_stock (ingredient_id, id),
    constraint fk_ingredient_stock foreign key (ingredient_id) references ingredient (id)
) engine=InnoDB default charset=utf8;
