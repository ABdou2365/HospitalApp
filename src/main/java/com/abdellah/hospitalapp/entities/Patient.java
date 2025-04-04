package com.abdellah.hospitalapp.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

//Here we didn't work with @Data because sometimes it causes some problems

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString @Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 3, max = 20, message = "Le prénom doit être entre 3 et 20 caractères")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 20, message = "Le nom doit être entre 3 et 20 caractères")
    private String lastName;

    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateOfBirth;

    @Min(value = 0, message = "Le score doit être positif")
    private int score;

    private boolean isSick;
}

