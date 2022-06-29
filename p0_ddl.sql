create table bank_user(
	user_id serial primary key,
	username varchar(30) unique not null,
	passwd varchar(20) not null
);

create table account_type (
	type_id serial primary key,
	description varchar(10) not null
);

create table account (
	account_id serial primary key,
	type_id integer references account_type (type_id)
);

create table account_user (
	account_id integer references account (account_id),
	user_id integer references bank_user (user_id),
	PRIMARY KEY(account_id, user_id)
);

create table account_transaction (
	transaction_id serial primary key,
	account_id integer references account (account_id),
	transaction_datetime timestamp not null,
	amount real not null
);
