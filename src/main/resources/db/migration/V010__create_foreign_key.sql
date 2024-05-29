alter table product add constraint fk_product_snack_shop
foreign key (snack_shop_id) references snack_shop (id);

alter table group_permission add constraint fk_group_permission_permission
foreign key (permission_id) references permission (id);

alter table group_permission add constraint fk_group_permission_group
foreign key (group_id) references `group` (id);

alter table user_group add constraint fk_user_group_group
foreign key (group_id) references `group` (id);

alter table user_group add constraint fk_user_group_user
foreign key (user_id) references `user` (id);

alter table snack_shop_payment_method add constraint fk_snack_shop_payment_method_payment_method
foreign key (payment_method_id) references payment_method (id);

alter table snack_shop_payment_method add constraint fk_snack_shop_payment_method_snack_shop
foreign key (snack_shop_id) references snack_shop (id);