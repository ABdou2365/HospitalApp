package com.abdellah.hospitalapp.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class AppUser {
    @Id @GeneratedValue
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;
}
