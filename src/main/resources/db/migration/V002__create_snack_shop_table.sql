create table snack_shop (
	id bigint not null auto_increment,
	name varchar(80) not null,
	registration_date datetime not null,
	update_date datetime null,
	active tinyint(1) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;