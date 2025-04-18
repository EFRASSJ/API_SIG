package org.example.api.Config.InitMongo;

import org.example.api.Repository.UserRepository;
import org.example.api.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class InitMongoData {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 2 ) {
                User user = new User();
                user.setEmail("admin@email.com");
                user.setPassword("admin");
                user.setRoles(List.of("ROLE_ADMIN"));
                String hashedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(hashedPassword);
                userRepository.save(user);
                System.out.println("Usuario inicial creado.");
            } else {
                System.out.println("Ya existen usuarios en la base.");
            }
        };
    }
}