-- USER
drop table if exists user_x;
create table user_x(
	id_user uuid not null,
	username varchar(50) not null unique,
	password varchar(255) not null,	
	first_name varchar(60) not null,
	last_name varchar(60)not null,
	email varchar(255) not null unique,
	phone_number varchar(60) not null,
	role varchar(50) not null default 'ROLE_USER',
	date_entry timestamp default current_timestamp,
	state int not null default 1,
	primary key(id_user)
);

-- TOKEN
drop table if exists user_token;
create table user_token(
	id_token uuid not null,
	value varchar(300) not null unique,
	primary key(id_token)
);

-- ADDRESS
drop table if exists address;
create table address(
	id_address uuid not null,
	city varchar(125),
	direction varchar(255),
	primary key(id_address)
);

-- USER ADDRESS
drop table if exists user_address;
create table user_address(
	fk_user uuid not null,
	fk_address uuid not null,
	constraint user_adres_user foreign key(fk_user) references user_x(id_user) on delete cascade,
	constraint user_addr_addr foreign key(fk_address) references address(id_address) on delete cascade
);

-- PRODUCT
drop table if exists product;
create table product(
	id_product uuid not null,
	name varchar(255) not null,
	description varchar(255),
	price numeric(19, 2) not null,
	count int not null,
	img_url varchar(255),
	state int default 1,
	primary key(id_product)
);

-- TAGS
drop table if exists tag;
create table tag(
	id_tag uuid not null,
	name varchar(255) not null,
	state int default 1,
	primary key(id_tag)
);

-- PRODUCTS and TAGS
drop table if exists product_tag;
create table product_tag(
	fk_product uuid not null,
	fk_tag uuid not null,
	constraint fk_prod_tag_prod foreign key(fk_product) references product(id_product),
	constraint fk_prod_tag_tag foreign key(fk_tag) references tag(id_tag)
);

-- ITEM
drop table if exists item;
create table item(
	id_item uuid not null,
	fk_product uuid not null,
	unit_price numeric(19, 2) null,
	quantity int,
	constraint fk_item_prod foreign key(fk_product) references product(id_product),
	primary key (id_item)
);

-- CART
drop table if exists cart;
create table cart(
	id_cart uuid not null,
	fk_user uuid not NULL unique,
	constraint fk_cart_clie foreign key(fk_user) references user_x(id_user),
	primary key(id_cart)
);

-- CART ITEM
drop table if exists  cart_item;
create table cart_item(
	fk_cart uuid not null,
	fk_item uuid not null,
	constraint fk_cart_item_cart foreign key(fk_cart) references cart(id_cart) ON UPDATE cascade,
	constraint fk_cart_item_item foreign key(fk_item) references item(id_item) ON UPDATE CASCADE ON DELETE cascade
);

-- INVOICE HEADER
drop table if exists invoice_header;
create table invoice_header(
	id_invoice_hea uuid not null,
	fk_user uuid not null,
	fk_cart uuid not null,
	sub_total numeric(19,2) not null,
	total numeric(19, 2) not null,
	date_creation timestamp default current_timestamp,
	state int default 1,
	constraint fk_invo_head_user foreign key(fk_user) references user_x(id_user),
	constraint fk_invo_head_cart foreign key(fk_cart) references cart(id_cart),
	primary key(id_invoice_hea) 
);

-- INVOICE DETAIl
drop table if exists invoice_detail;
create table invoice_detail(
	id_invoice_det uuid not null,
	fk_invoice uuid not null,
	fk_item uuid not null,
	state int default 1,
	constraint fk_invo_deta_invo foreign key(fk_invoice) references invoice_header(id_invoice_hea),
	constraint fk_invo_deta_item foreign key(fk_item) references item(id_item)ON UPDATE CASCADE ON DELETE cascade,
	primary key(id_invoice_det)
);
