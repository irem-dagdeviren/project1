package org.project.service;

import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.entity.Auth;


public interface AuthService {
    Auth register(RegisterRequestDTO dto);
    Boolean login(LoginRequestDTO dto);
}
