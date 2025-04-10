package com.abdellah.hospitalapp.security.service;

import com.abdellah.hospitalapp.security.entities.AppRole;
import com.abdellah.hospitalapp.security.entities.AppUser;
import com.abdellah.hospitalapp.security.repo.AppRoleRepository;
import com.abdellah.hospitalapp.security.repo.AppUserRepository;
import groovy.transform.Trait;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private AppRoleRepository roleRepository;
    private AppUserRepository userRepository;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser user = userRepository.findByUsername(username);
        if (user != null) throw new UsernameNotFoundException("User already exists");
        if(password.equals(confirmPassword)) throw new RuntimeException("Passwords do not match");
        user = AppUser.builder().username(username).password(password).email(email).build();
        return userRepository.save(user);
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = roleRepository.findById(role).orElse(null);
        if(appRole != null) throw new RuntimeException("Role already exists");
        appRole = AppRole.builder().role(role).build();
        return roleRepository.save(appRole);
    }

    @Override
    public void AddRoleToUser(String username, String role) {
        AppUser user = userRepository.findByUsername(username);
        AppRole appRole = roleRepository.findById(role).orElseThrow(() -> new UsernameNotFoundException("Role does not exist"));
        if(user == null) throw new RuntimeException("User not found");
        user.getRoles().add(appRole);
    }

    @Override
    public void RemoveRoleFromUser(String username, String role) {
        AppUser user = userRepository.findByUsername(username);
        AppRole appRole = roleRepository.findById(role).orElse(null);
        if(user == null) throw new RuntimeException("User not found");
        user.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
