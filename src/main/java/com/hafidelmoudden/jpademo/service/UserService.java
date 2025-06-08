package com.hafidelmoudden.jpademo.service;

import com.hafidelmoudden.jpademo.entities.Role;
import com.hafidelmoudden.jpademo.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName,String roleName);
    User authenticate(String userName,String password);
}