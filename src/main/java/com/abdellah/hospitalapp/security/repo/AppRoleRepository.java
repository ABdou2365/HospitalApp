package com.abdellah.hospitalapp.security.repo;

import com.abdellah.hospitalapp.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
