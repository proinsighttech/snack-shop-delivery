create table user (
	id bigint not null auto_increment,
	name varchar(80) not null,
    cpf varchar(14),
	email varchar(255) not null,
	password varchar(255) not null,
	registration_date datetime not null,

	primary key (id),
    unique key (cpf)
) engine=InnoDB default charset=utf8;