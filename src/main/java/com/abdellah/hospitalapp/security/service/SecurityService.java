package com.abdellah.hospitalapp.security.service;

import com.abdellah.hospitalapp.security.entities.AppRole;
import com.abdellah.hospitalapp.security.entities.AppUser;

public interface SecurityService {
    AppUser addNewUser(String username, String password, String email,String confirmPassword);
    AppRole addNewRole(String role);
    void AddRoleToUser(String username, String role);
    void RemoveRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);
}

