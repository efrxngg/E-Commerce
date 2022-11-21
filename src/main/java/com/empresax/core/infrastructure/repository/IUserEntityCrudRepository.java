package com.empresax.core.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresax.core.infrastructure.entity.UserEntity;

public interface IUserEntityCrudRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Query(value = """
        update user_x set
		    username = :use,
		    password = :pas,
		    first_name = :fir,
		    last_name = :las,
		    email = :ema,
		    phone_number = :pho
	    where id_user = :id ;
    """, nativeQuery = true)
    void updateUser(
            @Param("id") UUID id,
            @Param("use") String username,
            @Param("pas") String password,
            @Param("fir") String first_name,
            @Param("las") String last_name,
            @Param("ema") String email,
            @Param("pho") String phone_number);

    @Modifying
    @Query(value = "update user_x set state = 0 where id_user = id;", nativeQuery = true)
    void deletionLogical(@Param("id") UUID id);

    @Query(value = "select cast(id_user as varchar) from user_x where username = :un", nativeQuery = true)
    Optional<UUID> findIdByUsername(@Param("un") String username);

    @Query(value = "select * from user_x where username = :option or email = :option", nativeQuery = true)
    Optional<UserEntity> findByUsernameOrEmail(@Param("option") String option);

    @Query(value = "select * from user_x where username = :username or email = :email", nativeQuery = true)
    List<UserEntity> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

}