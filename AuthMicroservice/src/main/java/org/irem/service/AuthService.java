package org.irem.service;

import org.irem.dto.request.LoginRequestDTO;
import org.irem.dto.request.RegisterRequestDTO;
import org.irem.entity.Auth;


public interface AuthService {
    Auth register(RegisterRequestDTO dto);
    Boolean login(LoginRequestDTO dto);
}
