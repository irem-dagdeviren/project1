package org.project.service.impl;

import org.project.dto.request.LoginRequestDTO;
import org.project.entity.Auth;
import org.project.entity.Token;
import org.project.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BasicAuthTokenServiceImpl implements TokenService {
    @Override
    public Token createToken(Auth auth, LoginRequestDTO creds) {
        String usernamePassword = creds.getUserName() + ":" + creds.getPassword();
        String token = Base64.getEncoder().encodeToString(usernamePassword.getBytes());
        return new Token("Basic", token);
    }

    @Override
    public Auth verifyToken(String AuthHeader) {
        return null;
    }

    @Override
    public void logout(String tokenWithPrefix) {

    }
}
