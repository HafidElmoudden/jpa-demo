package com.hafidelmoudden.jpademo.web;

import com.hafidelmoudden.jpademo.entities.User;
import com.hafidelmoudden.jpademo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users/{username}")
    public User user(@PathVariable String username) {
        return userService.findUserByUserName(username);
    }
}