package com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.repository;

import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
