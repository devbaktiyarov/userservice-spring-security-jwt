package com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.service;

import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.domain.Role;
import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,  String roleName);
    User getUser(String username);
    List<User> getUsers();
}
