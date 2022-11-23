-- UserEntity
-- USER LOGICAL DELETION
drop procedure if exists sp_deletionlogical;
create procedure sp_deletionlogical(in id uuid )
language sql as 
$BODY$
	update user_x set state = 0 where id_user = id;
$BODY$;

call sp_deletionlogical('a77a88f3-5ae2-4266-bd77-678bdd313e42');

-- USER UPDATE 
drop procedure if exists sp_updateuser;
create procedure sp_updateuser(
	in id uuid, 
	in use varchar, 
	in pas varchar, 
	in fir varchar, 
	in las varchar,
	in ema varchar,
	in pho varchar
	)
language sql as 
$BODY$
	update user_x set
		username = use,
		password = pas,
		first_name = fir,
		last_name = las,
		email = ema,
		phone_number = pho
	where id_user = id ;
$BODY$;

call sp_updateuser('ed78a0f9-eeaf-490e-9106-2d93ce1aff94', 'a', 'b', 'c', 'd', 'e', 'd');
select * from user_x ux ;

 -- UPDATE QUANTITY CART ITEM
drop procedure if exists updateQuantityCartItem;
create procedure updateQuantityCartItem(in quant int, in id_i uuid, in id_c uuid)
language sql as
$BODY$
	update item as i set quantity = quant  from cart_item  ci 
	where i.id_item = id_i 
	and ci.fk_cart = id_c;
$BODY$;

call updateQuantityCartItem(1, '761f2f80-5bc3-4e07-a953-b9498492e21c', '75f137cc-0846-42af-80bc-de06519e8b26');

select * from user_x ;
SELECT concat(u.first_name, ' ', u.last_name)  FROM USER_X u where u.id_user = 'd340ef23-e518-4580-9849-ba34e44c7fb2';

select * from user_x ux2 ;

SELECT ci.fk_item  FROM cart_item ci 
INNER JOIN cart c ON ci.fk_cart=c.id_cart 
inner join user_x ux on c.fk_user = ux.id_user 
where ux.id_user = 'd340ef23-e518-4580-9849-ba34e44c7fb2';

delete from item i where i.id_item in (SELECT ci.fk_item  FROM cart_item ci 
INNER JOIN cart c ON ci.fk_cart=c.id_cart 
inner join user_x ux on c.fk_user = ux.id_user 
where ux.id_user = 'd340ef23-e518-4580-9849-ba34e44c7fb2');


select * from item;

select * from item i 
inner join invoice_detail id on i.id_item = id.fk_item  
where id.id_invoice_det = 'f545aa96-68b8-4945-9530-755925179f47';

update item as i set quantity = 1 from invoice_detail id
where i.id_item = id.fk_item and id.id_invoice_det = 'f545aa96-68b8-4945-9530-755925179f47';



-- CORRECCION
select * from item i ;
select * from invoice_detail id ;
select * from cart_item ci2 ;
select * from item i inner join cart_item  ci on i.id_item = ci.fk_item  
	and i.id_item = 'fbc2c430-9510-4ab7-bf9c-8679648d88ee';

select * from user_x ux ;

select * from invoice_detail id 
inner join invoice_header ih on id.fk_invoice = ih.id_invoice_hea 
where ih.fk_user = '52834af7-23de-46e3-b632-8638105cfab3';


select * from invoice_header ih2 ;
select * from invoice_detail id2 ;
select * from invoice_detail id 
inner join invoice_header ih on id.fk_invoice = ih.id_invoice_hea 
where ih.id_invoice_hea = 'af7e632e-17ae-4f95-b207-b9885c2fc35c' and ih.fk_user = '52834af7-23de-46e3-b632-8638105cfab3';


 
select * from invoice_detail id where id.id_invoice_det = 'f545aa96-68b8-4945-9530-755925179f47' and id.fk_invoice  = 'af7e632e-17ae-4f95-b207-b9885c2fc35c';

update invoice_detail set state = 1 where id_invoice_det = 'f545aa96-68b8-4945-9530-755925179f47' and fk_invoice  = 'af7e632e-17ae-4f95-b207-b9885c2fc35c';

update item i set quantity = 1 from invoice_detail id 
where i.id_item = id.fk_item and id.id_invoice_det = 'd6ba415c-b12d-46b7-8a4e-bc2f3e72a873' and id.fk_invoice = 'ae0b41db-e79c-48d4-a752-1215dff0e7b7';

select * from item i ;


update invoice_header ih set ih.state = :state where id_invoice_hea = :id_invoice_hea  and ih.fk_user = :id_user
