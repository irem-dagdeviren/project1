package org.irem.repository;

import org.irem.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Boolean existsByUserNameAndPassword(String username, String password);
}
