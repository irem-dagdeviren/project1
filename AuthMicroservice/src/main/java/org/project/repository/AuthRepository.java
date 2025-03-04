package org.project.repository;

import org.project.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Boolean existsByUserNameAndPassword(String username, String password);
    Auth findByUserName(String username);
    Auth findByEmail(String email);
    Auth findByActivationToken(String token);
}
