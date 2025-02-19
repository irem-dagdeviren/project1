package org.project.service;

import jakarta.validation.Valid;
import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.dto.response.LoginResponse;
import org.project.entity.Auth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AuthService {
    Auth register(RegisterRequestDTO dto);
    Boolean hasRegistered(LoginRequestDTO dto);
    Page<Auth> getAll(Pageable pageable);
    void activate(String token);
    LoginResponse authenticate(@Valid LoginRequestDTO dto);
    void logout(String tokenWithPrefix);
}
