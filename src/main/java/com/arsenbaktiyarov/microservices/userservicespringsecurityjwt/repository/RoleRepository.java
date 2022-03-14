package com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.repository;

import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
