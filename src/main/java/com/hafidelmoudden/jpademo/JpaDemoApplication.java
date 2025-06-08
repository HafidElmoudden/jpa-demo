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
            User firstUser = new User();
            firstUser.setUsername("hafid");
            firstUser.setPassword("hafid321");
            userService.addNewUser(firstUser);
            
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("1234");
            userService.addNewUser(adminUser);
            
            String[] roleNames = {"STUDENT", "USER", "ADMIN"};
            for (String name : roleNames) {
                Role role = new Role();
                role.setRoleName(name);
                userService.addNewRole(role);
            }
            
            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("admin", "USER");
            
            try {
                User authenticatedUser = userService.authenticate("user1", "1234");
                System.out.println("User ID: " + authenticatedUser.getUserId());
                System.out.println("Password: " + authenticatedUser.getPassword());
                System.out.println("User roles:");
                
                authenticatedUser.getRoles().forEach(role -> {
                    System.out.println("- " + role);
                });
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        };

    }
}