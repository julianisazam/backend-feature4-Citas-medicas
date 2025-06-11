// InitRolesData.java
package com.feature4.auth_service.config;

import com.feature4.auth_service.model.Rol;
import com.feature4.auth_service.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitRolesData implements CommandLineRunner {

    private RolRepository rolRepository;

    public InitRolesData(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Inicializar roles si no existen
        if (!rolRepository.findByNombre(Rol.RolNombre.ROLE_USER).isPresent()) {
            Rol userRole = new Rol();
            userRole.setNombre(Rol.RolNombre.ROLE_USER);
            rolRepository.save(userRole);
        }

        if (!rolRepository.findByNombre(Rol.RolNombre.ROLE_ADMIN).isPresent()) {
            Rol adminRole = new Rol();
            adminRole.setNombre(Rol.RolNombre.ROLE_ADMIN);
            rolRepository.save(adminRole);
        }

        if (!rolRepository.findByNombre(Rol.RolNombre.ROLE_DOCTOR).isPresent()) {
            Rol doctorRole = new Rol();
            doctorRole.setNombre(Rol.RolNombre.ROLE_DOCTOR);
            rolRepository.save(doctorRole);
        }
    }
}