package com.abdellah.hospitalapp.repository;

import com.abdellah.hospitalapp.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Use Pageable when you want to allow Spring Data JPA to handle pagination dynamically

    Page<Patient> findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String lastName,Pageable pageable);
}
