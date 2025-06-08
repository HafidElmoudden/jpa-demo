package com.hafidelmoudden.jpademo;

import com.hafidelmoudden.jpademo.entities.Role;
import com.hafidelmoudden.jpademo.entities.User;
import com.hafidelmoudden.jpademo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            User user1=new User();
            user1.setUsername("hafid");
            user1.setPassword("hafid321");
            userService.addNewUser(user1);
        };

    }
}