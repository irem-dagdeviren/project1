package org.project.service.impl;

import org.project.dto.request.LoginRequestDTO;
import org.project.entity.Auth;
import org.project.entity.Token;
import org.project.repository.AuthRepository;
import org.project.service.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Service
public class BasicAuthTokenServiceImpl implements TokenService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final AuthRepository authRepository;

    public BasicAuthTokenServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public Token createToken(Auth auth, LoginRequestDTO creds) {
        String usernamePassword = creds.getUserName() + ":" + creds.getPassword();
        String token = Base64.getEncoder().encodeToString(usernamePassword.getBytes());
        return new Token("Basic", token);
    }

    @Override
    public Long verifyToken(String authHeader) {
        if(Objects.isNull(authHeader) || authHeader.isEmpty()) {
            return null;
        }
        String base64Token = authHeader.substring(6).trim();
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(base64Token);
            String decodedToken = new String(decodedBytes, StandardCharsets.UTF_8); // Specify UTF-8
            String[] credentials = decodedToken.split(":");
            if (credentials.length != 2) {
                throw new IllegalArgumentException("Invalid Basic authentication format.");
            }
            String username = credentials[0];
            String password = credentials[1];

            Auth inDB = authRepository.findByUserName(username);
            if (inDB == null || !bCryptPasswordEncoder.matches(password, inDB.getPassword())) {
                return null;
            }
            return inDB.getId();

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void logout(String tokenWithPrefix) {

    }
}
