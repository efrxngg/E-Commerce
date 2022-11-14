// package com.empresax.core;

// import java.util.Date;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.empresax.core.infrastructure.entity.RoleType;
// import com.empresax.core.infrastructure.entity.StateType;
// import com.empresax.core.infrastructure.entity.UserEntity;
// import com.empresax.core.infrastructure.repository.IUserEntityCrudRepository;

// @Component
// public class ConsoleApplication implements CommandLineRunner {

//     @Autowired
//     private IUserEntityCrudRepository userRepo;
//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Override
//     public void run(String... args) throws Exception {
//         var e = new UserEntity();
//         e.setUsername("efrxngg");
//         e.setPassword(passwordEncoder.encode("root1234"));
//         e.setFist_name("Efren");
//         e.setLast_name("Galarza");
//         e.setEmail("efren@gmail.com");
//         e.setPhone_number("0954943114");
//         e.setRole("ROLE_ADMIN");
//         e.setState(StateType.ACTIVE);
//         e.setDate_entry(new Date());

//         var r = userRepo.save(e);
//         System.out.println(r);
//         userRepo.findAll().forEach(System.out::println);
//     }

// }