package org.project.service;

import org.project.dto.request.LoginRequestDTO;
import org.project.entity.Auth;
import org.project.entity.Token;

public interface TokenService {

    public Token createToken(Auth auth, LoginRequestDTO creds);
    public Auth verifyToken (String AuthHeader);
    void logout(String tokenWithPrefix);
}
