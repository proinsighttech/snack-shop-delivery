create table snack_shop_user_admin (
    snack_shop_id bigint not null,
    user_id bigint not null,

    primary key (snack_shop_id, user_id)
) engine=InnoDB default charset=utf8;

alter table snack_shop_user_admin add constraint fk_snack_shop_user_snack_shop
foreign key (snack_shop_id) references snack_shop (id);

alter table snack_shop_user_admin add constraint fk_snack_shop_user_user
foreign key (user_id) references `user` (id);