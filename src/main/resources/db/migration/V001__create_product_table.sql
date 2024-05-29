create table product (
	id bigint not null auto_increment,
	snack_shop_id bigint not null,
	name varchar(80) not null,
	description text not null,
	price decimal(10,2) not null,
	active tinyint(1) not null,
	category varchar(30) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;