package org.irem.service.impl;

import lombok.RequiredArgsConstructor;
import org.irem.dto.request.LoginRequestDTO;
import org.irem.dto.request.RegisterRequestDTO;
import org.irem.entity.Auth;
import org.irem.repository.AuthRepository;
import org.irem.service.AuthService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    public Auth register(RegisterRequestDTO dto) {
        return authRepository.save(Auth.builder()
                .userName(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build());
    }

    public Boolean login(LoginRequestDTO dto) {
        return authRepository.existsByUserNameAndPassword(dto.getUserName(), dto.getPassword());
    }
}
