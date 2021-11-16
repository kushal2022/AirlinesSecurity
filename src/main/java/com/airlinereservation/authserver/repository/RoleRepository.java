package com.airlinereservation.authserver.repository;

import com.airlinereservation.authserver.model.Role;
import com.airlinereservation.authserver.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   Role findByRole(RoleName roleName);
}

