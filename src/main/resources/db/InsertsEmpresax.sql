-- ========INSERTS=========
-- USER
insert into user_x (id_user, username, password, first_name, last_name, email, phone_number)
values('d340ef23-e518-4580-9849-ba34e44c7fb2', 'efrxngg', 'root1234', 'Efren', 'Galarza', 'efren@gmail.com', '0954943114');
select * from user_x ;

-- ADDRESS
insert into address values ('263dad59-8807-4c1b-bd1b-2f407c2cc57c', 'Duran', 'Coop bella vista mx sx');
select * from address ;

--USER ADDRESS
insert into user_address values('d340ef23-e518-4580-9849-ba34e44c7fb2', '263dad59-8807-4c1b-bd1b-2f407c2cc57c');
select * from user_address ua ;

-- PRODUCT
insert into product values ('94c0b5ce-d514-4221-b02b-2c6cae45ce04', 'Jujutzu no kaizen', '<3', 99.9, 1, 'img.png');
insert into product values ('9227e5ac-3d56-4656-b125-cf963281d716', 'Tate no Yuusha no Nariagari', '?', 9.9, 1, 'img.png');
insert into product values ('b23627f5-83f2-4b04-a74d-9326189f5fab', 'Attack on Titan', '<3', 99.9, 1, 'img.png');
insert into product values ('1be5aaa2-ab15-4a1d-81bc-3421f4a43040', 'Demon Slayer', '<3', 99.9, 1, 'img.png');
insert into product values ('6870a218-a801-4b53-9246-e70ce5f224ed', 'Nanatsu no taizai', '<3', 99.9, 1, 'img.png');
select * from product;

-- TAG
insert into tag values ('c74d8c50-9e4e-4f84-b5e3-01c139bf1481', 'Manga');
insert into tag values ('6cf6bab3-4d1d-40e6-942a-179e247293f0', 'Anime');
insert into tag values ('972bcd3d-c98a-455b-8d54-2af6b9d7dc45', 'Book');
insert into tag values ('6403470b-519b-47e2-a81f-1ea9390fe6b1', 'Demon');
select * from tag;

-- PRODUCT TAG
insert into product_tag values('94c0b5ce-d514-4221-b02b-2c6cae45ce04', 'c74d8c50-9e4e-4f84-b5e3-01c139bf1481');
insert into product_tag values('94c0b5ce-d514-4221-b02b-2c6cae45ce04', '6cf6bab3-4d1d-40e6-942a-179e247293f0');
insert into product_tag values('94c0b5ce-d514-4221-b02b-2c6cae45ce04', '6403470b-519b-47e2-a81f-1ea9390fe6b1');
insert into product_tag values('94c0b5ce-d514-4221-b02b-2c6cae45ce04', '972bcd3d-c98a-455b-8d54-2af6b9d7dc45');
delete from product_tag where fk_product = '94c0b5ce-d514-4221-b02b-2c6cae45ce04' and fk_tag = '972bcd3d-c98a-455b-8d54-2af6b9d7dc45';
select * from product_tag; 

-- CART
insert into cart values('d79c3b7a-dd5c-434c-998c-a3cbf468c4dd', 'd340ef23-e518-4580-9849-ba34e44c7fb2');
select * from cart;

-- ITEM 
insert into item VALUES ('fbc2c430-9510-4ab7-bf9c-8679648d88ee', '94c0b5ce-d514-4221-b02b-2c6cae45ce04', 99.9, 1);
select * from item;

-- CART ITEM
insert into cart_item values('d79c3b7a-dd5c-434c-998c-a3cbf468c4dd', 'fbc2c430-9510-4ab7-bf9c-8679648d88ee');
select * from cart_item ci;

-- INVOICE HEADER
insert into invoice_header(id_invoice_hea, fk_user, fk_cart, sub_total, total) 
values('52745f72-08da-4e30-840a-cc8470b844f2', 'd340ef23-e518-4580-9849-ba34e44c7fb2', 'd79c3b7a-dd5c-434c-998c-a3cbf468c4dd', 99.9, 111.888);
select * from invoice_header;

-- INVOICE DETAIL
insert into invoice_detail 
values ('ccda02b3-0199-4bd5-9ea1-b39c9c7cd1e1', '52745f72-08da-4e30-840a-cc8470b844f2', 'fbc2c430-9510-4ab7-bf9c-8679648d88ee');
select * from invoice_detail id ;

-- select * from gen_random_uuid();








