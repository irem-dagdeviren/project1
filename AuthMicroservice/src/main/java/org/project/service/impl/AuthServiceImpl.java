package org.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.entity.Auth;
import org.project.manager.UserProfileManager;
import org.project.repository.AuthRepository;
import org.project.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final UserProfileManager userProfileManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Auth register(RegisterRequestDTO dto) {
        Auth auth = authRepository.save(Auth.builder()
                .userName(dto.getUsername())
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build());

        userProfileManager.createUser(CreateUserRequestDTO.builder()
                .authId(auth.getId())
                .email(auth.getEmail())
                .username(auth.getUserName())
                .build());
        return auth;
    }

    public Boolean hasRegistered(LoginRequestDTO dto) {
        return authRepository.existsByUserNameAndPassword(dto.getUserName(), dto.getPassword());
    }

    @Override
    public List<Auth> getAll() {
        return authRepository.findAll();
    }
}
