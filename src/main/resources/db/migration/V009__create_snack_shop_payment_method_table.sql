create table snack_shop_payment_method (
	snack_shop_id bigint not null,
	payment_method_id bigint not null,

	primary key (snack_shop_id, payment_method_id)
) engine=InnoDB default charset=utf8;