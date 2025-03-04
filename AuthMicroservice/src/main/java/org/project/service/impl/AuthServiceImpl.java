package org.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.dto.AuthDTO;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.dto.response.LoginResponse;
import org.project.email.EmailService;
import org.project.entity.Auth;
import org.project.entity.Token;
import org.project.exception.*;
import org.project.manager.UserProfileManager;
import org.project.repository.AuthRepository;
import org.project.service.AuthService;
import org.project.service.TokenService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final UserProfileManager userProfileManager;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final TokenService tokenService;

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

    @Override
    public LoginResponse authenticate(LoginRequestDTO dto) {
        Auth auth = authRepository.findByUserName(dto.getUserName());
        if(Objects.isNull(auth)){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        if(!bCryptPasswordEncoder.matches(dto.getPassword(), auth.getPassword())){
            throw new AuthenticationException();
        }
        Token token = tokenService.createToken(auth, dto);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUser(new AuthDTO(auth));
        return loginResponse;
    }

    @Override
    public void logout(String tokenWithPrefix) {
            tokenService.logout(tokenWithPrefix);
        }

    @Override
    public Auth updateUser(Long id, String email,String username, String authHeader) {
        Long loggedInUser = tokenService.verifyToken(authHeader);
        if(Objects.isNull(loggedInUser)) {
            throw new NotFoundException(authHeader);
        }
        Auth inDB = authRepository.findById(id).orElseThrow();
        if(loggedInUser.equals(inDB.getId())) {
            inDB.setUserName(username);
            inDB.setEmail(email);
            authRepository.save(inDB);
            return inDB;

        }
        throw new AuthorizationException();
    }
}
