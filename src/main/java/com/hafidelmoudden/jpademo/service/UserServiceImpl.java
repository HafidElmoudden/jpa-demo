package com.hafidelmoudden.jpademo.service;

import lombok.AllArgsConstructor;
import com.hafidelmoudden.jpademo.entities.Role;
import com.hafidelmoudden.jpademo.entities.User;
import com.hafidelmoudden.jpademo.repositories.RoleRepository;
import com.hafidelmoudden.jpademo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = findUserByUserName(userName);
        Role role = findRoleByRoleName(roleName);
        if (user != null && role != null) {
            if (user.getRoles() != null) {
                user.getRoles().add(role);
                role.getUsers().add(user);
            }
        } else {
            if (user == null) {
                System.err.println("User not found: " + userName);
            }
            if (role == null) {
                System.err.println("Role not found: " + roleName);
            }
        }
    }

    @Override
    public User authenticate(String userName, String password) {
        User user = findUserByUserName(userName);
        if (user == null)
            throw new RuntimeException("Bad credentials");
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}