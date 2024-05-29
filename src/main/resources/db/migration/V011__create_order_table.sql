create table `order` (
  id bigint not null auto_increment,
  code varchar(36) not null,
  total decimal(10,2) not null,

  snack_shop_id bigint not null,
  user_client_id bigint not null,
  payment_method_id bigint not null,


  status varchar(30) not null,

  reception_date datetime not null,
  cancel_date datetime null,
  preparation_date datetime null,
  confirmation_date datetime null,
  finalization_date datetime null,

  primary key (id),

  constraint uk_order_code unique(code),
  constraint fk_order_snack_shop foreign key (snack_shop_id) references snack_shop (id),
  constraint fk_order_user_client foreign key (user_client_id) references `user` (id),
  constraint fk_order_payment_method foreign key (payment_method_id) references payment_method (id)
) engine=InnoDB default charset=utf8;
