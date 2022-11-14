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

