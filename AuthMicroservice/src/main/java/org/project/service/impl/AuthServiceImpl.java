package org.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.email.EmailService;
import org.project.entity.Auth;
import org.project.exception.ActivationException;
import org.project.exception.InvalidTokenException;
import org.project.exception.NotUniqueEmailException;
import org.project.manager.UserProfileManager;
import org.project.repository.AuthRepository;
import org.project.service.AuthService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final UserProfileManager userProfileManager;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Transactional(rollbackFor = MailException.class)
    public Auth register(RegisterRequestDTO dto) {
        try{
            Auth auth = authRepository.saveAndFlush(Auth.builder()
                    .userName(dto.getUsername())
                    .email(dto.getEmail())
                    .activationToken(UUID.randomUUID().toString())
                    .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                    .build());

            userProfileManager.createUser(CreateUserRequestDTO.builder()
                    .authId(auth.getId())
                    .email(auth.getEmail())
                    .username(auth.getUserName())
                    .build());
            emailService.sendActivationMail(auth.getEmail(), auth.getActivationToken());
            return auth;
        }catch(DataIntegrityViolationException exception){
            throw new NotUniqueEmailException();
        }catch (MailException exception){
            throw new ActivationException();
        }
    }


    public Boolean hasRegistered(LoginRequestDTO dto) {
        return authRepository.existsByUserNameAndPassword(dto.getUserName(), dto.getPassword());
    }

    @Override
    public Page<Auth> getAll(Pageable page) {
        return authRepository.findAll(page);
    }

    @Override
    public void activate(String token) {
        Auth inDB = authRepository.findByActivationToken(token);
        if(Objects.isNull(inDB)){
            throw new InvalidTokenException();
        }
        inDB.setActive(true);
        inDB.setActivationToken(null);
        userProfileManager.activateUser(inDB.getId());
        authRepository.save(inDB);

    }
}
