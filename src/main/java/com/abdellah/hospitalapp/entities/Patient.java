package com.abdellah.hospitalapp.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

//Here we didn't work with @Data because sometimes it causes some problems

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter
@Setter
@ToString @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int score;
    private boolean isSick;
}
