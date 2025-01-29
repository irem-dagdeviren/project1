package org.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.entity.Auth;
import org.project.manager.UserProfileManager;
import org.project.repository.AuthRepository;
import org.project.service.AuthService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final UserProfileManager userProfileManager;

    public Auth register(RegisterRequestDTO dto) {
        Auth auth = authRepository.save(Auth.builder()
                .userName(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build());

        userProfileManager.createUser(CreateUserRequestDTO.builder()
                .authId(auth.getId())
                .email(auth.getEmail())
                .username(auth.getPassword())
                .build());
        return auth;
    }

    public Boolean login(LoginRequestDTO dto) {
        return authRepository.existsByUserNameAndPassword(dto.getUserName(), dto.getPassword());
    }
}
