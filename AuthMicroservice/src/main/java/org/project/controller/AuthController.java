package org.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.entity.Auth;
import org.project.service.AuthService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.project.config.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTHSERVICE)
public class AuthController {
    private final AuthService authService;
    private final MessageSource messageSource;

    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@Valid @RequestBody RegisterRequestDTO dto) {
        // to demonstration of messages localizations
        String message = messageSource.getMessage("auth.registration.successful", null, LocaleContextHolder.getLocale());
        System.out.println(message);
        return new ResponseEntity<>(authService.register(dto), HttpStatus.CREATED);
    }

    @PostMapping(REGISTERED)
    public ResponseEntity<Boolean> hasRegistered(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.hasRegistered(dto));

    }

    @GetMapping(TOKEN)
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/get-all")
    public List<Auth> getAll() {
        return authService.getAll();
    }
}
