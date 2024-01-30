create table order_item (
  id bigint not null auto_increment,
  quantity smallint(6) not null,
  unit_price decimal(10,2) not null,
  total_price decimal(10,2) not null,
  observation varchar(255) null,
  order_id bigint not null,
  product_id bigint not null,

  primary key (id),
  unique key uk_order_item_product (order_id, product_id),

  constraint fk_order_item_order foreign key (order_id) references `order` (id),
  constraint fk_order_item_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;