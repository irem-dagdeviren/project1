package org.project.controller;

import lombok.RequiredArgsConstructor;
import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.entity.Auth;
import org.project.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.project.config.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTHSERVICE)
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody RegisterRequestDTO dto) {
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new RuntimeException("Passwords do not match");
        return new ResponseEntity<>(authService.register(dto), HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));

    }
}
