package com.arsenbaktiyarov.microservices.userservicespringsecurityjwt;

import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.domain.Role;
import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.domain.User;
import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceSpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceSpringSecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Ryan", "ryan", "spring",
                    new ArrayList<>()));
            userService.saveUser(new User(null, "James", "james", "spring",
                    new ArrayList<>()));
            userService.saveUser(new User(null, "Tony", "tony", "spring",
                    new ArrayList<>()));

            userService.addRoleToUser("ryan", "ROLE_USER");
            userService.addRoleToUser("tony", "ROLE_ADMIN");
            userService.addRoleToUser("james", "ROLE_MANAGER");
        };
    }

}
