create table product_ingredient (
    id bigint not null auto_increment,
    quantity smallint(6) not null,
    product_id bigint not null,
    ingredient_id bigint not null,

    primary key (id),
    unique key uk_product_ingredient (product_id, ingredient_id),

    constraint fk_product_ingredient_product foreign key (product_id) references product (id),
    constraint fk_product_ingredient_ingredient foreign key (ingredient_id) references ingredient (id)
) engine=InnoDB default charset=utf8;