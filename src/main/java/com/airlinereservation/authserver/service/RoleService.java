package com.airlinereservation.authserver.service;

import com.airlinereservation.authserver.model.Role;
import com.airlinereservation.authserver.model.RoleName;
import com.airlinereservation.authserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class RoleService {

    @Autowired
    private RoleRepository roleRepository;



    public Role findByRole(RoleName roleName) {
        return roleRepository.findByRole(roleName);

    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

}
