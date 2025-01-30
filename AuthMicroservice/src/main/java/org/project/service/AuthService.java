package org.project.service;

import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.entity.Auth;

import java.util.List;


public interface AuthService {
    Auth register(RegisterRequestDTO dto);
    Boolean hasRegistered(LoginRequestDTO dto);
    List<Auth> getAll();
}
